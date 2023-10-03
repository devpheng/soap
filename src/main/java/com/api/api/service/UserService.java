package com.api.api.service;

import com.api.api.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    public User create(User user);

    public Optional<User> get(long id);

    public List<User> getAll();

    public boolean update(User user);

    public boolean delete(long id);
}
