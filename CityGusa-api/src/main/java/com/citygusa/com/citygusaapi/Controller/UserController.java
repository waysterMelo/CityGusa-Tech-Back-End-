package com.citygusa.com.citygusaapi.Controller;

import com.citygusa.com.citygusaapi.Dto.UserDto;
import com.citygusa.com.citygusaapi.Entity.UserEntity;
import com.citygusa.com.citygusaapi.Exceptions.ErrorAuthentication;
import com.citygusa.com.citygusaapi.Repository.UserRepo;
import com.citygusa.com.citygusaapi.Service.IMPL.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    public void copyEntityToDto(UserEntity entity, UserDto dto){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setDateRegistration(dto.getDateRegistration());
    }

    @PostMapping("/authenticated")
    private ResponseEntity<?> authenticated(@RequestBody UserDto userDto) {
       try {
           UserEntity userEntity = userServiceImpl.authenticate(userDto.getName(), userDto.getPassword());
           return ResponseEntity.ok(userEntity);
       }catch (ErrorAuthentication e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id){
        Optional<UserEntity> user = userService.getUserById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
