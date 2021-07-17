package com.springbootsecurity.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
    private String username;
    private String password;
}
