package com.newnocturnalhunter.api_rest.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorMsg {
    private String message;
    private String url;
}
