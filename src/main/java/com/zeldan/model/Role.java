package com.zeldan.model;

import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long roleId;

    private String name;

    private String description;

    @ManyToMany(fetch = LAZY)
    private Set<Privilege> privileges = new HashSet<>(0);

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Set<Privilege> privileges) {
        this.privileges = privileges;
    }

}
