package org.andres_k.web.app.utils.data;

import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.exception.EError;
import org.andres_k.web.app.utils.tools.TFiles;
import org.andres_k.web.app.utils.tools.TPath;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configs {
    private boolean valid;
    private final String fileName;
    private Properties tmpProperties;
    private Properties confProperties;


    public void init() throws AppException {
        this.valid = true;
        this.confProperties = new Properties();
        this.tmpProperties = new Properties();

        try {
            InputStream stream = TFiles.getResourceAsStream(this.fileName);
            this.confProperties.load(stream);
        } catch (Exception e) {
            this.valid = false;
            throw new AppException(EError.CONFIGS_FILE, e);
        }

        try {
            this.verifyConfigs();
        } catch (AppException e) {
            this.valid = false;
            throw e;
        }

        try {
            InputStream stream = TFiles.getFileInput(TPath.configPath(this.getConfProperty(ECProperty.CONFIG_TMP)));
            this.tmpProperties.load(stream);
        } catch (Exception ignored) {
        }
    }

    public boolean isValid() {
        return this.valid;
    }

    /**
     * Config Properties
     */
    public String getConfProperty(ECProperty key) {
        return this.confProperties.getProperty(key.value);
    }

    public boolean hasConfProperty(ECProperty key) {
        return this.confProperties.containsKey(key.value);
    }

    private void verifyConfigs() throws AppException {
        if (!this.hasConfProperty(ECProperty.CONFIG_TMP)) {
            this.confProperties.setProperty("tmp_config", "configs/tmp.properties");
        }
        if (!this.hasConfProperty(ECProperty.SERVER_URL))
            throw new AppException(EError.CONFIGS_SERVER_URL, new Throwable());
    }

    /**
     * TMP Properties
     */
    public boolean hasProperty(ETProperty key) {
        return this.tmpProperties.containsKey(key.value);
    }

    public String getProperty(ETProperty key) {
        return this.tmpProperties.getProperty(key.value);
    }

    public void addProperty(ETProperty key, String value) throws AppException {
        this.tmpProperties.setProperty(key.value, value);
        try {
            this.saveProperties();
        } catch (IOException e) {
            throw new AppException(EError.TMP_CONFIGS_FILE, e);
        }
    }

    private void saveProperties() throws IOException {
        OutputStream stream = TFiles.getFileOutput(TPath.configPath(this.getConfProperty(ECProperty.CONFIG_TMP)));

        this.tmpProperties.store(stream, "");
    }

    /**
     * SINGLETON
     **/
    private static Configs instance;

    private Configs() {
        this.fileName = "/configs/application.properties";
        this.valid = false;
    }

    public static Configs get() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }
}
