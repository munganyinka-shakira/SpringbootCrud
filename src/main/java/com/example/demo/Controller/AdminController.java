package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/removeDuplicates")
    public ResponseEntity<String> removeDuplicateUsers() {
        userService.removeDuplicateUsers();
        return ResponseEntity.ok("Duplicate users removed successfully");
    }
}

