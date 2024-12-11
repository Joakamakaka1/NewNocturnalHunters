package com.newnocturnalhunter.api_rest.dto;

import lombok.*;

/**
 * The type Cliente login dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLoginDTO {
    private String username;
    private String password;
    private String email;
}
