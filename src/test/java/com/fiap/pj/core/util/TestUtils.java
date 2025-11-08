package com.fiap.pj.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static String objectToJson(Object value) throws Exception {
        return mapper.writeValueAsString(value);
    }

    public static String buildURL(String... args) {
        var url = new StringBuilder("/");

        for (String arg : args) {
            if (url.length() > 1) {
                url.append("/");
            }
            url.append(arg);
        }

        return url.toString();
    }
}