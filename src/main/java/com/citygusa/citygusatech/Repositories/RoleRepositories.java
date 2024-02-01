package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.Roles;
import com.citygusa.citygusatech.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositories extends JpaRepository<Roles, Long> {
}
