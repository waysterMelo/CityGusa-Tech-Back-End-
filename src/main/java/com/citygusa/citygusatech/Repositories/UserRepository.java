package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.Roles;
import com.citygusa.citygusatech.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByRole(Roles rolesEntity);
}
