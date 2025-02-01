package com.myproject.journalApp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helthcheck {

    @GetMapping("/helthcheck")
    public String helth() {
        return "OK";
    }
}
