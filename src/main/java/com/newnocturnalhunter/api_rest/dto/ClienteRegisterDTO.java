package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String email;
    private String rol;
}
