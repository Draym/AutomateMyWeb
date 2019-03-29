package org.andres_k.web.app.utils.data;

import com.fasterxml.jackson.core.type.TypeReference;
import org.andres_k.web.app.utils.tools.TFiles;
import org.andres_k.web.app.utils.tools.TJson;
import org.andres_k.web.app.utils.tools.TPath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Configs {
    public static final String url = "http://localhost:9090/api/";
    private final String fileName;
    private Map<EProperty, String> properties;

    public void init() {
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasProperty(EProperty key) {
        return this.properties.containsKey(key);
    }

    public String getProperty(EProperty key) {
        return this.properties.get(key);
    }

    public void addProperty(EProperty key, String value) {
        this.properties.put(key, value);
        this.save();
    }

    private void save() {
        TFiles.writeIn(TPath.configPath(this.fileName), TJson.toString(this.properties, false));
    }

    private void load() throws IOException {
        String json = TFiles.readOut(TPath.configPath(this.fileName));

        if (json != null)
            this.properties = TJson.toObject(json, new TypeReference<Map<EProperty, String>>() {});
    }

    /**
     * SINGLETON
     **/
    private static Configs instance;

    private Configs() {
        this.properties = new HashMap<>();
        this.fileName = "configs/data_config.json";
    }

    public static Configs get() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }
}
