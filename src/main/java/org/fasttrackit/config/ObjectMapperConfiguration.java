package org.fasttrackit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperConfiguration {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    //static block
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

}
