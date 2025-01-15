package com.shopease.users.services;

import com.shopease.users.controller.UserApi;
import com.shopease.users.controller.request.UserRequest;
import com.shopease.users.dal.UserRepository;
import com.shopease.users.dto.UserDTO;
import com.shopease.users.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository uRep;

    @Transactional
    public UserDTO save(UserRequest request) throws Exception {
        if (Objects.requireNonNullElse(request.getId(), 0) != 0) {
            existsById(request.getId());
        }
        UserEntity userEntity = uRep.save(buildUserSaved(request));
        return buildModel(userEntity);
    }

    public void existsById(Integer id) throws Exception {
        if (!uRep.existsById(id)) {
            throw new Exception("Not Found");
        }
    }

    private UserDTO buildModel(UserEntity userEntity) {
        return new UserDTO(userEntity.getEmail(), userEntity.getPassword(), userEntity.getName());
    }

    private static UserEntity buildUserSaved(UserRequest request) {
        return new UserEntity(request.getEmail(), request.getPassword(), request.getName());
    }


}
