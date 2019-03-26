package org.andres_k.web.backend.utils.managers;

import org.andres_k.web.backend.utils.tools.TRandomString;

public class TokenManager {
    public static String generate() {
        return TRandomString.get().generate();
    }
}
