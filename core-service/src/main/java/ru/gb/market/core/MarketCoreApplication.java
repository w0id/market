package ru.gb.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarketCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketCoreApplication.class, args);
    }

}
