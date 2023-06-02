package com.liyichen125.dbfinalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailPageController {
    @GetMapping("/send-email")
    public String mailSender() {
        return "send-email";
    }
}
