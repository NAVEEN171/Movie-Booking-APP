package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Role;
import com.naveen.movieticketplatform.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
