package com.fu.prenancytracker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test", description = "Test APIs")
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user") // GET /api/auth/user
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> testUser() {
        return ResponseEntity.ok("User using this API");
    }

    @GetMapping("/admin") // GET /api/auth/admin
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> testAdmin() {
        return ResponseEntity.ok("Admin using this API");
    }

    @GetMapping("/testAPI") // GET /api/auth/admin
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Test using this API");
    }
}
