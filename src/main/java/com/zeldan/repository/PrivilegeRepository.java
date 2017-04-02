package com.zeldan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeldan.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
