package com.example.cqrsbankingapp.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class ObjectConverter implements AttributeConverter<Object, String> {

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
}
//
///**
// * @SneakyThrows allows bypassing the need to explicitly handle checked exceptions,
// * such as JsonProcessingException, which might be thrown by ObjectMapper methods.
// * It reduces boilerplate code but should be used with caution, as it hides potential exceptions.
// */

//public class ObjectConverter implements AttributeConverter<Object, String> {
//
//    @SneakyThrows
//    @Override
//    public String convertToDatabaseColumn(Object attribute) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(attribute);
//    }
//
//    @Override
//    public Object convertToEntityAttribute(String dbData) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.convertValue(dbData, Object.class);
//    }
//}