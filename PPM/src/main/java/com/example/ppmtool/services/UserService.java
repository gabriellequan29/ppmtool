package com.example.ppmtool.services;

import com.example.ppmtool.domain.User;
import com.example.ppmtool.exceptions.UserAlreadyExistsException;
import com.example.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setUsername(newUser.getUsername());
        return userRepository.save(newUser);
        } catch (Exception exception) {
            throw new UserAlreadyExistsException("User with username '"+ newUser.getUsername() +"' already exists");

        }

    }
}
