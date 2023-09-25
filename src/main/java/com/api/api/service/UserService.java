package com.api.api.service;

import com.api.api.model.User;
import java.util.Optional;

public interface UserService {

    public User create(User user);

    public Optional<User> get(long id);
    
}
