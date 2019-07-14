package com.zeldan.security;

import com.zeldan.model.Account;
import com.zeldan.model.Privilege;
import com.zeldan.model.Role;
import com.zeldan.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountByUsername = accountRepository.findByUsername(username);
        if (!accountByUsername.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        Account account = accountByUsername.get();
        if (account.getRoles() == null || account.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User not authorized.");
        }
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true, getAuthorities(account.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<Privilege> privileges = roles.stream()
                .map(Role::getPrivileges)
                .flatMap(Set::stream)
                .collect(toList());
        return privileges.stream()
                .map(Privilege::getName)
                .collect(toList());
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        return privileges.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

}