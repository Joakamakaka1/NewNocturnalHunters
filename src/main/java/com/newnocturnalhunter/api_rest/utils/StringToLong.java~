package com.newnocturnalhunter.api_rest.utils;

import org.springframework.stereotype.Component;

@Component
public class StringToLong {
    public Long method(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El id debe ser un número");
        }
    }
}
