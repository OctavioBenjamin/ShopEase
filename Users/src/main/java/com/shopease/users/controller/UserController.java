package com.shopease.users.controller;

import com.shopease.users.controller.request.UserLoginRequest;
import com.shopease.users.controller.request.UserRequest;
import com.shopease.users.dto.UserDTO;
import com.shopease.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(){
        return ResponseEntity.ok("Hola mundo");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(){
        return ResponseEntity.ok("Hola mundo");
    }

}
