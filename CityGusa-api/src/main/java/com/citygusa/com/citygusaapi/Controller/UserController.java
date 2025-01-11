package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.UserDto;
import com.citygusa.com.citygusaapi.Entity.UserEntity;
import com.citygusa.com.citygusaapi.Exceptions.ErrorAuthentication;
import com.citygusa.com.citygusaapi.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public void copyEntityToDto(UserEntity entity, UserDto dto) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());
        entity.setDateRegistration(dto.getDateRegistration());
    }

    @PostMapping("/autenticar")
    private ResponseEntity<?> authenticate(@RequestBody UserDto userDto) {
        try {
            UserEntity userEntity = userService.authenticate(userDto.getNome(), userDto.getSenha());
            return ResponseEntity.ok(userEntity);
        } catch (ErrorAuthentication e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        Optional<UserEntity> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        UserEntity entity = new UserEntity();
        copyEntityToDto(entity, userDto);
        UserEntity user_saved = userService.saveUser(entity);
        return ResponseEntity.ok(user_saved);
    }
}
