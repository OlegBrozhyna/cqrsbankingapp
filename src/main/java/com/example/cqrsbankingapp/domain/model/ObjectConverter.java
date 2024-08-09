package com.example.cqrsbankingapp.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ObjectConverter implements AttributeConverter<Object, String> {
    @Override
    public String convertToDatabaseColumn(Object o) {
        return null;
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        return null;
    }
}
