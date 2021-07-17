package com.springbootsecurity.models;

import lombok.*;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
