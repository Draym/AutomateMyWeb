package org.andres_k.web.app.utils.exception;

public enum  EError {
    HTTP_ERROR("The communication with the server encounter an error."),
    FXFACTORY_CREATE_NODE("Factory could not create the requested node"),
    CACHE_FILE_NOT_FOUND("The file has not been found in the cache"),
    CACHE_JSON_ERROR("The file can't be parsed as JSON"),
    CONFIGS_FILE("The application.properties file is missing or damaged."),
    CONFIGS_SERVER_URL("The application.properties file is missing or damaged."),
    TMP_CONFIGS_FILE("The tmp.properties file could not be created.");


    private String value;

    EError(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
