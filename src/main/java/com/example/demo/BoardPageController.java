package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardPageController {

    @GetMapping("/board")
    public String board() {
        return "board";
    }
}