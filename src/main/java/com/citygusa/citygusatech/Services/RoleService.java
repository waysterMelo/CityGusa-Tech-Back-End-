package com.citygusa.citygusatech.Services;

import com.citygusa.citygusatech.Dto.RoleDto;
import com.citygusa.citygusatech.Entity.Roles;
import com.citygusa.citygusatech.Entity.Users;
import com.citygusa.citygusatech.Repositories.RoleRepositories;
import com.citygusa.citygusatech.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepositories roleRepository;
    @Autowired
    private UserRepository userRepository;


    public void copyDtoToEntity(RoleDto dto, Roles role) {
        role.setRole(dto.getRole());
    }

    @Transactional
    public RoleDto insertRole(RoleDto roleDto){
        Roles roleEntity = new Roles();
        copyDtoToEntity(roleDto, roleEntity);
        roleEntity = roleRepository.save(roleEntity);
        return new RoleDto(roleEntity);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> getRoleDtoList(){
        List<Roles> lista = roleRepository.findAll();
        return lista.stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Transactional
    public RoleDto updateRole(RoleDto dto, Long id){
        Roles entity = roleRepository.getById(id);
        copyDtoToEntity(dto, entity);
        entity = roleRepository.save(entity);
        return new RoleDto(entity);
    }

    @Transactional
    public void deleteRoleWithRoles(Long id) {
        Optional<Roles> roles = roleRepository.findById(id);
        if (roles.isPresent()) {
            Roles rolesEntity = roles.get();

            // Desassocia a role dos usuários
            List<Users> usersWithRole = userRepository.findByRoles(rolesEntity);
            for (Users u : usersWithRole) {
                u.setRoles(null);
            }
            roleRepository.delete(rolesEntity);
        } else {
            // Lidar com a situação em que o papel não foi encontrado
            throw new EntityNotFoundException("Função não econtrada com o id : " + id);
        }
    }
}