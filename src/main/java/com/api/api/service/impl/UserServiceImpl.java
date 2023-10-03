package com.api.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.api.model.User;
import com.api.api.repository.UserRepository;
import com.api.api.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> get(long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean update(User user) {
        Optional<User> resUser = userRepository.findById(user.getId());
        if(resUser.isPresent()) {
            User userObj = resUser.get();
            userObj.setEmail(user.getEmail());
            userObj.setFirstName(user.getFirstName());
            userObj.setLastName(user.getLastName());
            userRepository.save(userObj);
            return true;
        }
        return false;
    }

    public boolean delete(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
}
