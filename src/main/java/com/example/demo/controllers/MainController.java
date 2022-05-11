package com.example.demo.controllers;

import com.example.demo.USDRate;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Controller
@EnableScheduling
public class MainController {
    @Autowired
    private USDRate usdRate;


    @GetMapping("/currencyRate")
    public String demo(Model model) throws IOException {
        model.addAttribute("clearUSD", usdRate.getClearUSD());
        return "usd";

    }

}


