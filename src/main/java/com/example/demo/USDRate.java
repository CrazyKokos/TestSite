package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Component
@EnableScheduling
public class USDRate {

    private String clearUSD;

    public void setClearUSD(String clearUSD) {
        this.clearUSD = clearUSD;
    }

    public String getClearUSD() {
        return clearUSD;
    }

    @Scheduled(fixedDelay = 60000) //Парсинг через 1 минуту.
    public String parsing() throws IOException {
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

        }
        return clearUSD;
    }

}

