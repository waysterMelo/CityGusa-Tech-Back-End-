package com.citygusa.citygusatech.Repositories;

import com.citygusa.citygusatech.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
