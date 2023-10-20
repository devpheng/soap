package com.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.api.model.LoginDTO;
import com.api.api.model.LoginResponseDTO;
import com.api.api.model.RegistrationDTO;
import com.api.api.model.User;
import com.api.api.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
     @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getFirstname(), body.getLastname(), body.getEmail(), body.getUsername(), body.getPassword());
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
