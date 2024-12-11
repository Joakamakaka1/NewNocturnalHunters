package com.newnocturnalhunter.api_rest.exceptions;

import lombok.*;

/**
 * The type Error msg.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsg {
    private String message;
    private String url;
}
