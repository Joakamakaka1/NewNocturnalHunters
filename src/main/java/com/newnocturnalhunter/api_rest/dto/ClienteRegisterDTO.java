package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

/**
 * The type Cliente register dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String email;
    private String rol;
}
