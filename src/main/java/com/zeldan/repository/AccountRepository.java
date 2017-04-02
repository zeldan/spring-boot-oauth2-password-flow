package com.zeldan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeldan.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(final String username);
}
