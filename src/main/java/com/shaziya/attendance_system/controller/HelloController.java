package com.shaziya.attendance_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // yeh http requests handle karega
public class HelloController {

    @GetMapping("/api/hello") //calls /api/hello method when someone visits it
    public String hello() {
        return "Attendance System Backend is Running!"; //http response 
    }
}