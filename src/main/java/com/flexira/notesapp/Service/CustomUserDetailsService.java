package com.flexira.notesapp.Service;
//This class is used to load user details in specific UserDetails format so that spring security can read it
//and compare passwords
import com.flexira.notesapp.Model.User;
import com.flexira.notesapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findUserByUsername(username);

        if(user==null){
            throw new RuntimeException(("User not found!"));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
            Collections.emptyList() //as we don't have any list of roles, we send and empty list
        );
    }
}
