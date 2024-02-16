package com.citygusa.citygusatech.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.citygusa.citygusatech.Api.Dto.UserDto;
import com.citygusa.citygusatech.Api.Dto.UserInsertDto;
import com.citygusa.citygusatech.Api.Entity.Roles;
import com.citygusa.citygusatech.Api.Entity.Users;
import com.citygusa.citygusatech.Repositories.UserRepository;
import com.citygusa.citygusatech.Services.UserService;

@SuppressWarnings("unused")
@DataJdbcTest
public class UserServiceTest {

    private long existingId;
	private long nonExistingId;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldSaveUser(){
       //criar um userinsertDto e User
       UserInsertDto dto = new UserInsertDto();
       dto.setName("admin");
       dto.setPassword("123");
       
       //configurar o comportamento dos mocks
       when(encoder.encode("123")).thenReturn("nova_senha");

       Users u = new Users();
       u.setId(1L);
       u.setName("admin");
       u.setPassword("nova_senha"); 

       when(repository.save(any(Users.class))).thenReturn(u);

       //metodo que quero testar
       UserDto result = service.insert(dto);

       //verificar se os metodos foram chamados com os argumentos esperados
       verify(encoder).encode("123");
       verify(repository).save(any(Users.class));

       //verificar o resultado do metodo
       assertNotNull(result); 
    }

    @Test
    public void testFindAllUsers(){
        //objetos
        List<Users> userList = Arrays.asList(
            new Users(1L, "user1"),
            new Users(2L, "user2")
    );

        //comportamentos esperado do repository
        when(repository.findAll()).thenReturn(userList);

        //metodo testado
        List<UserDto> result = service.findAll();

        //verificar se o metodo repository foi chamado corretamente
        verify(repository).findAll();

         // Verifique se o resultado do método é o esperado
         Assertions.assertNotNull(result);
         Assertions.assertEquals(userList.size(), result.size());

    }

    @Test
    public void testFindAllPaged_Success() {
          // Create sample users and pageable object
          Pageable pageable = PageRequest.of(0, 10);
          List<Users> users = Arrays.asList(new Users(1L, "user@gmail.com", "123", "user"));
          Page<Users> userPage = new PageImpl<>(users, pageable, users.size());
  
          // Mock UserRepository behavior
          Mockito.when(repository.findAll(pageable)).thenReturn(userPage);
  
          // Execute the method under test
          Page<UserDto> result = service.findAllPaged(pageable);
  
          // Assertions
          assertNotNull(result);
            Mockito.verify(repository, Mockito.times(1)).findAll(pageable);


   }


    
}
