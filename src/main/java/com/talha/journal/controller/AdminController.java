package com.talha.journal.controller;

import com.talha.journal.entity.User;
import com.talha.journal.service.EmailService;
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

    @Autowired
    private EmailService emailService;

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

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam String to,
                                            @RequestParam String subject,
                                            @RequestParam String body) {
        // Exception handling
        try {
            emailService.sendEmail(to, subject, body);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }

}
