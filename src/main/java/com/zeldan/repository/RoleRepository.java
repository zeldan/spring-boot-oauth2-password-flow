package com.zeldan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeldan.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
