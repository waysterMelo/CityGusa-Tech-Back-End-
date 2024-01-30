package com.citygusa.citygusatech.Controller;

import com.citygusa.citygusatech.Dto.RoleDto;
import com.citygusa.citygusatech.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto dto) {
        RoleDto newDto = roleService.insertRole(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getRoles(){
        List<RoleDto> roles = roleService.getRoleDtoList();
        return ResponseEntity.ok().body(roles);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto dto) {
        RoleDto updatedDto = roleService.updateRole(dto, id);
        return ResponseEntity.ok().body(updatedDto);
    }
}