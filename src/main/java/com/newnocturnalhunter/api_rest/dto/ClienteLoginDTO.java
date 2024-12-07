package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteLoginDTO {
    private String username;
    private String password;
    private String email;
}
