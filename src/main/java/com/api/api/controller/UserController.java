package com.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.api.model.User;
import com.api.api.service.UserService;

@Controller
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    ResponseEntity<String> create(@RequestBody User user) {
        User userRes = userService.create(user);
        return new ResponseEntity<>(userRes.toString(), HttpStatus.OK);
    }
}
