package com.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.api.model.User;
import com.api.api.service.UserService;
import java.util.Optional;
import com.google.gson.Gson;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;

    @PostMapping("/user")
    ResponseEntity<String> create(@RequestBody User user) {
        User userRes = userService.create(user);
        return new ResponseEntity<>(gson.toJson(userRes), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<String> get(@PathVariable Long id) {
        Optional<User>userRes = userService.get(id);
        if(userRes.isPresent()) {
            return new ResponseEntity<>(gson.toJson(userRes.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found!!!", HttpStatus.NOT_FOUND);
        
    }

    @GetMapping("/users")
    ResponseEntity<String> getAll() {
        List<User>users = userService.getAll();
        if(users.size() > 0) {
            return new ResponseEntity<>(gson.toJson(users), HttpStatus.OK);
        }
        return new ResponseEntity<>("There is no users", HttpStatus.OK);
        
    }

    @PatchMapping("/user")
    ResponseEntity<String> update(@RequestBody User user) {
        if(userService.update(user)) {
            return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> delete(@PathVariable Long id) {
        if(userService.delete(id)) {
            return new ResponseEntity<>("User id " + id + " has been deleted!!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found!!!", HttpStatus.NOT_FOUND);
    }
}
