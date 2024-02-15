package com.citygusa.citygusatech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.citygusa.citygusatech.Api.Entity.Roles;
import com.citygusa.citygusatech.Api.Entity.Users;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByRoles(Roles role);

    Users findByEmail(String email);

    boolean existsByEmail(String email);
}