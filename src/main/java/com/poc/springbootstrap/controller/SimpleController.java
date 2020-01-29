package com.poc.springbootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
 
    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
     
    @ResponseBody    
    @GetMapping("/home")
    public String homePage() {
        return "HOME";
    }
    
    @ResponseBody
    @GetMapping("/hello")
    public String helloPage() {
        return "Hello Service";
    }
}

//http://localhost:8081/