package com.shopease.users.services;

import com.shopease.users.dal.UserRepository;
import com.shopease.users.dto.UserDTO;
import com.shopease.users.dto.UserDTOMapper;
import com.shopease.users.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository uRep;

    @Autowired
    private UserDTOMapper mapper;

    public UserDTO findUserById(Integer id){
        UserEntity user = uRep.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User no found"));

        return mapper.apply(user);
    }
}
