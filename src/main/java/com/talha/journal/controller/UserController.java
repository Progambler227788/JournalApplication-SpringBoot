package com.talha.journal.controller;


import com.talha.journal.entity.User;
import com.talha.journal.service.JournalEntityService;
import com.talha.journal.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired  // dependency injection
    private UserEntityService userService;

    @GetMapping
    public List<User> getAllUsers(){
     return  userService.findUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    // I don't need now username manually as i've added authentication and it will come automatically


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser){
        // take name from authentication session
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();
        User userIn = userService.findByUserName(uname);
        if (userIn!=null){

            userIn.setUserName(newUser.getUserName());
            userIn.setPassword(newUser.getPassword());
            userService.saveNewUser(userIn);
        }
        // successfully updated
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserByName(){
        // take name from authentication session
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();

        userService.deleteUserByName(uname);
        // successfully deleted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
