package com.citygusa.com.citygusaapi.Service;

import com.citygusa.com.citygusaapi.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserServie {

    UserEntity authenticate(String name, String password);

    Optional<UserEntity> getUserById(Integer id);
}
