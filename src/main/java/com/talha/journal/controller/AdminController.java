package com.talha.journal.controller;

import com.talha.journal.entity.User;
import com.talha.journal.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        // gives all users
        List<User> all = userEntityService.findUsers();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public void createUser(@RequestBody User admin){

        userEntityService.saveAdminUser(admin);
    }
}
