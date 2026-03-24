package com.flexira.notesapp.Controller;

import com.flexira.notesapp.Model.User;
import com.flexira.notesapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/{username}")
    public String removeUser(@PathVariable String username){
        return userService.removeUser(username);
    }
}
