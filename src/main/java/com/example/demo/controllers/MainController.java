package com.example.demo.controllers;

import com.example.demo.USDRate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.IOException;

@Controller
@EnableScheduling
public class MainController {
    @Autowired
    private USDRate usdRate;


    @GetMapping("/usd")
    public String demo(Model model) throws IOException {
        model.addAttribute("clearUSD", usdRate.getClearUSD());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            model.addAttribute("Username", username);
        } else {
            String username = principal.toString();
            model.addAttribute("Username", username);
        }

        return "usd";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}


