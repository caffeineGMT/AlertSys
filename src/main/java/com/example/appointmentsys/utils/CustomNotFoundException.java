package com.example.appointmentsys.utils;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(Long id) {
        super("not found" + id);
    }
}
