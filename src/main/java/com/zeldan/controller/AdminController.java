package com.zeldan.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    public String admin() {
        return "admin can access this endpoint";
    }
}
