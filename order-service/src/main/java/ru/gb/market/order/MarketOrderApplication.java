package ru.gb.market.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
//@EnableWebFluxSecurity
public class MarketOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketOrderApplication.class, args);
    }
}