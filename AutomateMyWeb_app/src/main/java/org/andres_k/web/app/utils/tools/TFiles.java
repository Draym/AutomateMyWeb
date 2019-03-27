package org.andres_k.web.app.utils.tools;

import java.io.InputStream;
import java.net.URL;

public class TFiles {
    public static URL getResource(String fileName) {
        return TFiles.class.getResource((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }

    public static InputStream getResourceAsStream(String fileName) {
        return TFiles.class.getResourceAsStream((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }
}
