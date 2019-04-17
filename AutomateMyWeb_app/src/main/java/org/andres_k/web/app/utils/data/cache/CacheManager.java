package org.andres_k.web.app.utils.data.cache;

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
    private final Class<T> instance;

    public CacheManager(ECProperty localFolder, Class<T> instance) {
        this.localFolder = localFolder;
        this.localFile = "_cache_tmp.json";
        this.instance = instance;

        this.history = new HashMap<>();
        this.keys = new ArrayList<>();
        try {
            List<String> files = TFiles.getFilesNameIn(TPath.cachePath(this.localFolder.value));
            for (String file : files) {
                this.keys.add(file.substring(0, file.indexOf(this.localFile)));
            }
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
        String pathFile = this.getFilePath(key);
        String jsonValue = TFiles.readOut(TPath.cachePath(pathFile));

        if (jsonValue == null)
            throw new AppException(EError.CACHE_FILE_NOT_FOUND, new Throwable());
        try {
            T item = TJson.toObject(jsonValue, this.instance);
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

    // TODO queue this job
    protected void persist(String key) {
        String pathFile = this.getFilePath(key);

        if (this.history.containsKey(key)) {
            String jsonValue = TJson.toString(this.history.get(key).get(this.history.get(key).size() - 1));
            TFiles.writeIn(TPath.cachePath(pathFile), jsonValue);
        } else {
            TFiles.remove(TPath.cachePath(pathFile));
        }
    }

    private String getFilePath(String key) {
        return this.localFolder.value + "/" + key + this.localFile;
    }

    public List<T> getChanges() {
        List<T> changes = new ArrayList<>();

        for (Map.Entry<String, List<T>> entry : this.history.entrySet()) {
            if (entry.getValue().size() > 0)
                changes.add(entry.getValue().get(entry.getValue().size() - 1));
        }

        return changes;
    }
}
