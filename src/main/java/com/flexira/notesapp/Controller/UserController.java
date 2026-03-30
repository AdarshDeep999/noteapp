package com.flexira.notesapp.Controller;

import com.flexira.notesapp.Model.User;
import com.flexira.notesapp.Repository.UserRepository;
import com.flexira.notesapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public User createUser(@RequestBody User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); //taking pass and encoding and then again setting in same user
        return userService.createUser(user);
    }
/*
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/{username}")
    public String removeUser(@PathVariable String username){
        return userService.removeUser(username);
    }

 */
}
