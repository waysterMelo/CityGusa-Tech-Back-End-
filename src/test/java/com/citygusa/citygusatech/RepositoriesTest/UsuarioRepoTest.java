package com.citygusa.citygusatech.RepositoriesTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.citygusa.citygusatech.Api.Entity.Users;
import com.citygusa.citygusatech.Repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepoTest {

    @Autowired
    private UserRepository repository;

    private Users criarUser(){
            return Users.builder().name("wayster").email("waystermelo@gmail.com")
            .password("123").build();
    }

    @Test
    public void shouldVerifyEmailExists(){
        Users user = criarUser();
        user = repository.save(user);
        boolean rs =  repository.existsByEmail(user.getEmail());
        Assertions.assertTrue(rs);
    }
    
    @Test
    public void mustReturnFalseWhenEmailNotExists(){
        boolean rs = repository.existsByEmail("admin@example.com");
        Assertions.assertFalse(rs);
    }


    @Test
    public void shouldReturnTrueWhenFindID(){
        Users users = criarUser();
        users = repository.save(users);
        Long id = users.getId();
        boolean rs = repository.existsById(id);
        Assertions.assertNotNull(rs);
    }

    @Test
    public void shouldReturnFalseWhenNotFindID(){
        boolean rs = repository.existsById(1123L);  
         Assertions.assertFalse(rs);
    }

}
