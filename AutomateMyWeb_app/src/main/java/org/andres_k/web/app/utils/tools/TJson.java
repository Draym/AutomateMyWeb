package org.andres_k.web.app.utils.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TJson {

    public static String toString(Object object, boolean defaultTyping) {
        ObjectMapper mapper = new ObjectMapper();
        if (defaultTyping)
            mapper.enableDefaultTyping();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Console.log_err("TJson: " + e.getMessage());
        }
        return null;
    }

    public static String toString(Object object) {
        return toString(object, true);
    }

    public static <T> T toObject(String json, Class<T> classValue) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        return mapper.readValue(json, classValue);
    }

    public static <T> T toObject(String json, TypeReference classValue) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, classValue);
    }
}
