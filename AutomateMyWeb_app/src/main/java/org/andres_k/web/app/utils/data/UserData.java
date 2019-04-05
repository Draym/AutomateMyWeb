package org.andres_k.web.app.utils.data;

import org.andres_k.web.app.utils.data.cache.ACache;

public class UserData {
    private ACache templates;
    private ACache directives;
    private ACache scripts;

    /**
     * SINGLETON
     **/
    private static UserData instance;

    private UserData() {
    }

    public static UserData get() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
}
