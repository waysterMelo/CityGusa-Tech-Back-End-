package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity authenticate(String name, String password);

    Optional<UserEntity> getUserById(Integer id);
}
