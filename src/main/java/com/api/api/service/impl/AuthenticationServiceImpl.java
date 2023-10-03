package com.api.api.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.api.model.LoginResponseDTO;
import com.api.api.model.Role;
import com.api.api.model.User;
import com.api.api.repository.RoleRepository;
import com.api.api.repository.UserRepository;
import com.api.api.service.AuthenticationService;
import com.api.api.service.TokenService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{
     @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String firstName, String lastName, String email, String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByRole("USER").get();

        Set<Role> roles = new HashSet<>();

        roles.add(userRole);

        return userRepository.save(new User(null, firstName, lastName, email, username, encodedPassword, roles));
    }

    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}
