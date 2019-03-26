package org.andres_k.web.backend.utils.managers;

import org.andres_k.web.backend.utils.tools.THashString;

public class PasswordManager {
    public static String hashPassword(String password) throws THashString.CannotPerformOperationException {
        return THashString.createHash(password);
    }

    public static void verifyPassword(String password, String correct) throws Exception {
        if (!THashString.compare(password, correct))
            throw new SecurityException("The password is incorrect");
    }
}
