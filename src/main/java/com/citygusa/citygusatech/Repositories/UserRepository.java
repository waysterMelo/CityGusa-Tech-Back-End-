package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.Roles;
import com.citygusa.citygusatech.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByRoles(Roles role);

    Users findByEmail(String email);
}