package com.example.luntan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
//        model.addAttribute("name",name);
//        @RequestParam(name = "name")String name, Model model
        return "index";
    }
}
