package com.shopease.users.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private Integer id;
    private String email;
    private String password;
    private String name;
}
