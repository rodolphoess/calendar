package com.calendar.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONProcessor {

	public synchronized static <T> T toObject(String jsonText, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonText, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public synchronized static String toJSON(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }

	}

}
