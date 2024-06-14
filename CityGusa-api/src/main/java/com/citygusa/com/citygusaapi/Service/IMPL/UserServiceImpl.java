package com.citygusa.com.citygusaapi.Service.IMPL;

import com.citygusa.com.citygusaapi.Entity.UserEntity;
import com.citygusa.com.citygusaapi.Exceptions.ErrorAuthentication;
import com.citygusa.com.citygusaapi.Repository.UserRepo;
import com.citygusa.com.citygusaapi.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity authenticate(String name, String password) {
        Optional<UserEntity> user_name =  userRepo.findByName(name);
        if (!user_name.isPresent()) {
            throw new ErrorAuthentication("Usuário não encontrado para o email informado.");
        }
        boolean key_ok = user_name.get().getPassword().equals(password);
        if (!key_ok) {
            throw new ErrorAuthentication("Senha Inválida");
        }
        return user_name.get();
    }

    @Override
    public Optional<UserEntity> getUserById(Integer id) {
      try {
          logger.info("Procurando user com o id: {}", id);
          Optional<UserEntity> user =  userRepo.findById(id);

          if (user.isPresent()) {
              logger.info("Usuario encontrado" + user.get());
              return user;
          }else {
              logger.warn("Erro ao encontrar o user com o id: {}", id);
              return Optional.empty();
          }
      }catch (Exception e){
          logger.error("Erro ao encontrar o user com o id: {}", id);
          throw new RuntimeException(e);
      }

    }


    @Transactional
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userRepo.save(user);
    }
}
