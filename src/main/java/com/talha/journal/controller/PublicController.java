package com.talha.journal.controller;


import com.talha.journal.entity.User;
import com.talha.journal.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntityService userService;


    @GetMapping("health-check")
    // we have mapped our end point with this method
    // when I hit this endpoint, my control will come here at Get-Mapping
    public String healthCheck(){
        return  "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}
