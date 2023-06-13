package com.ezybytes.springsecuritybasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelocmeController {

    @GetMapping(value = {"", "/","welcome"})
    public String welcome() {
        return "Welcome to Spring Application with security";
    }

}
