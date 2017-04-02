package com.zeldan.config;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.zeldan.model.Account;
import com.zeldan.model.Privilege;
import com.zeldan.model.Role;
import com.zeldan.repository.AccountRepository;
import com.zeldan.repository.PrivilegeRepository;
import com.zeldan.repository.RoleRepository;

@Component
public class DefaultDataGenerator {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    public DefaultDataGenerator(final AccountRepository accountRepository, final RoleRepository roleRepository, final PrivilegeRepository privilegeRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @PostConstruct
    public void generateDefaultData() {
        final Privilege privilege = new Privilege();
        privilege.setName("PRIVILEGE_READ");
        privilege.setDescription("privilege read description");
        privilegeRepository.save(privilege);

        final Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setDescription("role admin description");
        role.setPrivileges(Collections.singleton(privilege));
        roleRepository.save(role);

        final Account account = new Account();
        account.setUsername("username");
        account.setPassword("$2a$06$aqwKPUsa2hxC8EfDgx1QX.D.DvIRPLjJJF75xWPaorj9vR8xY5fpS");
        account.setRoles(Collections.singleton(role));
        accountRepository.save(account);
    }

}
