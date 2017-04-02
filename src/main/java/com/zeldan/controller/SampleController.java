package com.zeldan.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/ping")
    @PreAuthorize("hasAuthority('PRIVILEGE_READ')")
    public String test() {
        return "ping";
    }
}
