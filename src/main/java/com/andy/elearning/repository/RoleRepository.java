package com.andy.elearning.repository;

import com.andy.elearning.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> getRoleByName(String roleName);
}
