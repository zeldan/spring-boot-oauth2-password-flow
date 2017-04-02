package com.zeldan.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zeldan.model.Account;
import com.zeldan.model.Privilege;
import com.zeldan.model.Role;
import com.zeldan.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<Account> accountByUsername = accountRepository.findByUsername(username);
        if (!accountByUsername.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        final Account account = accountByUsername.get();
        if (account.getRoles() == null || account.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User not authorized.");
        }
        final User userDetails = new User(account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true,
                getAuthorities(account.getRoles()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(@NotNull final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(@NotNull final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(@NotNull final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}