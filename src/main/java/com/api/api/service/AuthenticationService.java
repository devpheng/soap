package com.api.api.service;

import com.api.api.model.LoginResponseDTO;
import com.api.api.model.User;

public interface AuthenticationService {
    public User registerUser(String firstName, String lastName, String email, String username, String password);

    public LoginResponseDTO loginUser(String username, String password);
}
