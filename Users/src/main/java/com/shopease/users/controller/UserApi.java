package com.shopease.users.controller;

import com.shopease.users.controller.request.UserLoginRequest;
import com.shopease.users.controller.request.UserRequest;
import com.shopease.users.dto.UserDTO;
import com.shopease.users.dto.UserDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface UserApi {
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    UserDTO login(@RequestBody UserLoginRequest request);

    @PostMapping(path = "/users/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    UserDTO saveUser(@RequestBody UserRequest request) throws Exception;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserDTOMapper findById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization") String token) throws Exception;

    @GetMapping(path = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserDTO findCurrentUser(@RequestHeader(value = "Authorization") String token) throws Exception;


    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    String test() throws Exception;
}
