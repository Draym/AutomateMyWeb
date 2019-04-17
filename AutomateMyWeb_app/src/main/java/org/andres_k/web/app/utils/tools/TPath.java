package org.andres_k.web.app.utils.tools;

public class TPath {
    public static String cachePath(String path) {
        return System.getProperty("java.io.tmpdir") + "/TMP_wigmo/" + path;
    }

    public static String localPath(String path) {
        return "./local/" + path;
    }
}
