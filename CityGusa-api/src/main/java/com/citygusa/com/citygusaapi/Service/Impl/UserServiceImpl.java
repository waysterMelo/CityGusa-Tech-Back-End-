package com.citygusa.com.citygusaapi.Service.Impl;

import com.citygusa.com.citygusaapi.Entity.UserEntity;
import com.citygusa.com.citygusaapi.Exceptions.ErrorAuthentication;
import com.citygusa.com.citygusaapi.Repository.UserRepo;
import com.citygusa.com.citygusaapi.Service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity authenticate(String nome, String password) {
        Optional<UserEntity> user_name =  userRepo.findByNome(nome);
        if (!user_name.isPresent()) {
            throw new ErrorAuthentication("Usuário não encontrado para o email informado.");
        }
       UserEntity userEntity = user_name.get();
        if (!userEntity.getSenha().equals(password)) {
            throw new ErrorAuthentication("Senha incorreta.");
        }
        return userEntity;
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
