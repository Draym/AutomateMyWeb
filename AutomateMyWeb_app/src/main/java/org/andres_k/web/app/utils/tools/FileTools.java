package org.andres_k.web.app.utils.tools;

import java.net.URL;

public class FileTools {
    public static URL getResource(String fileName) {
        return FileTools.class.getResource((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }
}
