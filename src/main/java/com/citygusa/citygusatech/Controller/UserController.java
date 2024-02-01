package com.citygusa.citygusatech.Controller;

import com.citygusa.citygusatech.Dto.UserDto;
import com.citygusa.citygusatech.Dto.UserInsertDto;
import com.citygusa.citygusatech.Dto.UserUpdateDto;
import com.citygusa.citygusatech.Services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserInsertDto userInsertDto){
        UserDto newUser = userService.insert(userInsertDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).body(newUser);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAllUsers(Pageable pageable){
        Page<UserDto> result = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        UserDto users = userService.findById(id);
        return ResponseEntity.ok(users);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto dto){
        UserDto result = userService.update(dto, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("USUARIO DELETADO COM SUCESSO", HttpStatus.OK);
    }
}