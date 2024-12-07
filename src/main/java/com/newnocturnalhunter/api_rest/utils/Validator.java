package com.newnocturnalhunter.api_rest.utils;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
