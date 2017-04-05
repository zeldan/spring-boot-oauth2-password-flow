package com.zeldan.config;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    public DefaultDataGenerator(final AccountRepository accountRepository, final RoleRepository roleRepository, final PrivilegeRepository privilegeRepository,
            final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void generateDefaultData() {
        accountRepository.save(createAccount("admin", "admin", "PRIVILEGE_ADMIN_READ", "ROLE_ADMIN"));
        accountRepository.save(createAccount("user", "user", "PRIVILEGE_USER_READ", "ROLE_USER"));
    }

    private Account createAccount(final String username, final String password, final String privilegeName, final String roleName) {
        final Privilege privilege = new Privilege();
        privilege.setName(privilegeName);
        privilegeRepository.save(privilege);

        final Role role = new Role();
        role.setName(roleName);
        role.setPrivileges(Collections.singleton(privilege));
        roleRepository.save(role);

        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setRoles(Collections.singleton(role));
        return account;
    }
}
