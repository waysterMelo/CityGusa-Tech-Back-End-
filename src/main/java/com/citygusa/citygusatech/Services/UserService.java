package com.citygusa.citygusatech.Services;

import com.citygusa.citygusatech.Api.Dto.RoleDto;
import com.citygusa.citygusatech.Api.Dto.UserDto;
import com.citygusa.citygusatech.Api.Dto.UserInsertDto;
import com.citygusa.citygusatech.Api.Dto.UserUpdateDto;
import com.citygusa.citygusatech.Api.Entity.Roles;
import com.citygusa.citygusatech.Api.Entity.Users;
import com.citygusa.citygusatech.Repositories.RoleRepositories;
import com.citygusa.citygusatech.Repositories.UserRepository;
import com.citygusa.citygusatech.Services.ExceptionsService.RegraNegocioException;
import com.citygusa.citygusatech.Services.ExceptionsService.ResourceNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
@SuppressWarnings("null")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepositories roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private void copyDtoToEntity(UserDto dto, Users user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.getRoles().clear();
        for (RoleDto roleDto : dto.getRoles()){
           
            Roles roleEntity = roleRepository.getOne(roleDto.getId());
            user.getRoles().add(roleEntity);
        }

    }

    @Transactional
    public UserDto insert(UserInsertDto insertDto){
        Users users = new Users();
        copyDtoToEntity(insertDto, users);
        users.setPassword(passwordEncoder.encode(insertDto.getPassword()));
        users = userRepository.save(users);
        return new UserDto(users);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll(){
        List<Users> lista = userRepository.findAll();
        return lista.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id){
        Optional<Users> result = userRepository.findById(id);
        Users users = result.orElseThrow(() -> new ResourceNotFoundException("Usuário "+id+" não foi encontrado"));
        return new UserDto(users);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable){
        Page<Users> list =  userRepository.findAll(pageable);
        return list.map(UserDto::new);
    }
    @Transactional(readOnly = true)
    public UserDto update(UserUpdateDto updateDto, Long id){
        try {
            Users users = userRepository.getOne(id);
            copyDtoToEntity(updateDto, users);
            users = userRepository.save(users);
            return new UserDto(users);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não foi encontrado" + id);
        }
    }

    @Transactional
    public void delete(@PathVariable Long id){
            userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username);
        if (users == null){
            log.error("Usuário não encontrado " + username);
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        log.info("Usuário encontrado " + username);
        return users;
    }

    public void validarEmail(String email) {
        boolean existeEmail =  roleRepository.existsByEmail(email);
        if (existeEmail) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }


    // @Override
	// public void validarEmail(String email) {
	// 	boolean existe = repository.existsByEmail(email);
	// 	if(existe) {
	// 		throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
	// 	}
	// }
}