package org.andres_k.web.app.utils.tools;

public class TPath {
    public static String configPath(String path) {
        return System.getProperty("java.io.tmpdir") + "/TMP_wigmo/" + path;
    }
}
