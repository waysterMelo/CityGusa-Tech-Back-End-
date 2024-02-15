package com.citygusa.citygusatech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citygusa.citygusatech.Api.Entity.Roles;

@Repository
public interface RoleRepositories extends JpaRepository<Roles, Long> {
}
