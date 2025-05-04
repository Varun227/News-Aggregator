package org.example.controller;


import org.example.Entity.User;
import org.example.Service.AuthenticationService;
import org.example.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Controller
public class UserController {

    @Autowired
    private AuthenticationService _authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO user) {
        // Logic to handle user registration
        User registeredUser =  _authenticationService.registerUser(user);
        String generatedToken = UUID.randomUUID().toString();
        String applicationURL = "http://localhost:8089/verifyRegistration?token=" + generatedToken;
        System.out.println("Verification link: " + applicationURL);
        _authenticationService.registerVerificationToken(registeredUser, generatedToken);
        return registeredUser;

        // Redirect to a success page
    }

    @PostMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam String token) {
        boolean isTokenValid = _authenticationService.validateVerificationToken(token);
        if(isTokenValid){
            // Logic to handle successful verification
            // For example, you can update the user's status in the database
            _authenticationService.enableUser(token);
            return "User verified successfully";

        }else{
            return "token is not valid";
        }
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
