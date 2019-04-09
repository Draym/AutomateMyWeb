package org.andres_k.web.app.utils.data.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import org.andres_k.web.app.core.http.models.UserProperty;
import org.andres_k.web.app.utils.data.ECProperty;
import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.exception.EError;
import org.andres_k.web.app.utils.tools.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager<T extends UserProperty> {
    protected Map<String, List<T>> history;
    protected List<String> keys;
    private final ECProperty localFolder;
    private final String localFile;

    public CacheManager(ECProperty localFolder) {
        this.localFolder = localFolder;
        this.localFile = "_cache_tmp.json";
        this.history = new HashMap<>();
        this.keys = new ArrayList<>();
        try {
            this.keys.addAll(TFiles.getFilesNameIn(TPath.configPath(this.localFolder.value)));
        } catch (FileNotFoundException e) {
            Console.log_err("The folder {" + localFolder.value + "} not found.");
        }
    }

    public T get(String key) throws AppException {
        if (this.history.containsKey(key)) {
            List<T> data = this.history.get(key);

            if (data != null && !data.isEmpty()) {
                return data.get(data.size() - 1);
            }
        }
        return this.retrieve(key);
    }

    public T previous(String key) {
        if (this.history.containsKey(key)) {
            List<T> data = this.history.get(key);

            if (data != null && !data.isEmpty()) {
                if (data.size() > 1)
                    data.remove(data.size() - 1);
                return data.get(data.size() - 1);
            }
        }
        return null;
    }

    public List<String> getKeys() {
        return this.keys;
    }

    private T retrieve(String key) throws AppException {
        String pathFile = this.localFolder + key + this.localFile;
        String jsonValue = TFiles.readOut(TPath.configPath(pathFile));

        if (jsonValue == null)
            throw new AppException(EError.CACHE_FILE_NOT_FOUND, new Throwable());
        try {
            T item = TJson.toObject(jsonValue, new TypeReference<T>() {
            });
            List<T> data = new ArrayList<>();
            data.add(item);
            this.history.put(key, data);
            return item;
        } catch (IOException e) {
            throw new AppException(EError.CACHE_JSON_ERROR, e);
        }
    }


    public void save(T item) {
        if (this.history.containsKey(item.getName())) {
            this.history.get(item.getName()).add(item);
        } else {
            List<T> data = new ArrayList<>();
            data.add(item);
            this.history.put(item.getName(), data);
            this.keys.add(item.getName());
        }
        this.persist(item.getName());
    }

    public void delete(T item) {
        this.history.remove(item.getName());
        this.persist(item.getName());
    }

    protected void persist(String key) {
        String pathFile = this.localFolder + key + this.localFile;

        if (this.history.containsKey(key)) {
            String jsonValue = TJson.toString(this.history.get(key));
            TFiles.writeIn(pathFile, jsonValue);
        } else {
            TFiles.remove(pathFile);
        }
    }
}
