package com.example.demo.controllers;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Controller
public class MainController {

    String clearUSD;

    @GetMapping("/")
    public String parsing(Model model) throws IOException {
        Document doc = Jsoup.connect("https://cbr.ru/").get();

        // 1. Найти HTML тэги, которые обозначены классом main-indicator-rate
        Elements rateUSD = doc.getElementsByClass("main-indicator_rate");

        //2. Из них, выбрать тот !тэг!, внтури которого есть тэг c классом "_dollar"
        for (int i = 0; i < rateUSD.size(); i++) {
            Element rateUSD01 = rateUSD.get(i);
            Elements rateUSD02 = rateUSD01.getElementsByClass("_dollar");
            if (!rateUSD02.isEmpty()) {
                System.out.println(rateUSD01.text());

                // 3. Из него извлекаем второй элемент с классом mono-num
                Elements rateUSD03 = rateUSD01.getElementsByClass("mono-num");
                Element rateUSD04 = rateUSD03.get(0);

                //4. Из полученного тэга получаем содержимое
                clearUSD = rateUSD04.text();
                break;
            } else {
                clearUSD = "Бабосики";

            }
            System.out.println(clearUSD);
            return clearUSD;
        }
        model.addAttribute("clearUSD", clearUSD);
        return "usd";
    }

}


