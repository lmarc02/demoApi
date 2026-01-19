package com.example.demo.api.controller;

import com.example.demo.api.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    private static final String API_KEY = "your-secret-api-key";

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id,
                        @RequestHeader("X-API-Key") String apiKey) {
        if (!API_KEY.equals(apiKey)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }

        Optional user = userService.getUser(id);

        if(user.isPresent()){
            return (User) user.get();
        }
        return null;
    }
}
