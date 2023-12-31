package com.api.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegistrationDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}
