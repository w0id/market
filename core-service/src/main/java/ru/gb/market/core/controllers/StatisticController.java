package ru.gb.market.core.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.services.StatisticService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
@AllArgsConstructor
public class StatisticController {

    private StatisticService statisticService;

    @GetMapping
    public List<String> getStatistic() {
        return statisticService.getStatistic();
    }
}