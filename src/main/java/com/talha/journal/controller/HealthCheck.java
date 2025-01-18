package com.talha.journal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @GetMapping("health-check")
    // we have mapped our end point with this method
    // when I hit this endpoint, my control will come here at Get-Mapping
    public String healthCheck(){
        return  "OK";
    }
}
