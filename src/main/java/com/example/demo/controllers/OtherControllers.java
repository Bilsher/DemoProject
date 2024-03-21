package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherControllers {

    @GetMapping("/Test1")
    public String test1(Model model) {
        model.addAttribute("title", "Test1 page");
        return "Test1";
    }

    @GetMapping("/Test2")
    public String test2(Model model) {
        model.addAttribute("title", "Test2 page");
        return "Test2";
    }
    @GetMapping("/Test3")
    public String test3(Model model) {
        model.addAttribute("title", "Test3 page");
        return "Test3";
    }

}
