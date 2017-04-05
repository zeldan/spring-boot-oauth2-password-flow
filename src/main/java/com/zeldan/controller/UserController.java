package com.zeldan.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('PRIVILEGE_USER_READ')")
    public String user() {
        return "user can access this endpoint";
    }
}
