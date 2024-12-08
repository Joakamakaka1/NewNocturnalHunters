package com.newnocturnalhunter.api_rest.utils;

import com.newnocturnalhunter.api_rest.model.Enemigos;
import com.newnocturnalhunter.api_rest.model.Personajes;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    public boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Método en Validator
    public boolean validateRole(String rol) {
        return "USER".equals(rol) || "ADMIN".equals(rol);
    }

    // Validación de username
    public boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.length() >= 3;
    }

    // Validación de password
    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.length() >= 6;
    }

    // Validación de tipo Enemigo
    public boolean validateTipoEnemigo(Enemigos.TipoEnemigo tipo) {
        if (tipo == null) {
            return false;
        }
        return tipo == Enemigos.TipoEnemigo.Basico ||
                tipo == Enemigos.TipoEnemigo.Intermedio ||
                tipo == Enemigos.TipoEnemigo.Boss;
    }

    // Validación de tipo Personaje
    public boolean validateTipoPersonaje(Personajes.TipoPersonaje tipo) {
        if (tipo == null) {
            return false;
        }
        return tipo == Personajes.TipoPersonaje.Espadachin ||
                tipo == Personajes.TipoPersonaje.Disparador ||
                tipo == Personajes.TipoPersonaje.Mele ||
                tipo == Personajes.TipoPersonaje.Tanque;
    }

    // Validación de fechas
    public boolean validateFecha(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }
}

