package org.andres_k.web.app.utils.data;

public enum ECProperty {
    SERVER_URL("server_url"),
    CONFIG_TMP("config_tmp"),
    CACHE_TEMPLATE("cache_template"),
    CACHE_DIRECTIVE("cache_directive"),
    CACHE_SCRIPT("cache_script");

    public String value;

    ECProperty(String value) {
        this.value = value;
    }
}

