package com.flexira.notesapp.Controller;

import com.flexira.notesapp.Dto.LoginDto;
import com.flexira.notesapp.Security.JwtUtil;
import com.flexira.notesapp.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService; //out method loadbyusername will be called as we have overridden it
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        String username = loginDto.getUsername();;
        String password = loginDto.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new RuntimeException("Invalid credential");
        }
        return jwtUtil.generateToken(username);
    }

}
