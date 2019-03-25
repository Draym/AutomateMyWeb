package org.andres_k.web.backend.utils;


import java.util.Base64;

public class EncryptTools {
    public static String encrypt(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String decrypt(String value){
        return new String(Base64.getDecoder().decode(value));
    }
}
