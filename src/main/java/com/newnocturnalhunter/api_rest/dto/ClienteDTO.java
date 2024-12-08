package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String rol;
}
