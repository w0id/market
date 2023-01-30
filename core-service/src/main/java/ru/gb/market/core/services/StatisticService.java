package ru.gb.market.core.services;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.SummaryServicesDuration;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class StatisticService {

    private final SummaryServicesDuration summaryServicesDuration;

    public List<String> getStatistic() {
        return summaryServicesDuration.getDuration()
                .entrySet()
                .stream()
                .map(i -> i.getKey() + ": " + i.getValue() + " ms")
                .toList();
    }
}

