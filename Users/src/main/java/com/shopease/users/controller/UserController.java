package com.shopease.users.controller;

import com.shopease.users.controller.request.UserLoginRequest;
import com.shopease.users.controller.request.UserRequest;
import com.shopease.users.dto.UserDTO;
import com.shopease.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController implements UserApi {

    @Autowired
    private UserService service;

    @Override
    public UserDTO login(UserLoginRequest request) {
        return service.validateUser(request);
    }

    @Override
    public UserDTO saveUser(UserRequest request) throws Exception {
        return service.save(request);
    }

    @Override
    public UserDTO findById(Integer id,final String token) throws Exception {
        service.findCurrentUser(token);
        return service.findByUser(id).withToken(token);
    }

    @Override
    public UserDTO findCurrentUser(String token) throws Exception {
        return service.findCurrentUser(token);
    }

    @Override
    public String test() throws Exception {
        return "hello mundo";
    }

}
