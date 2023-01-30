package ru.gb.market.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class SummaryServicesDuration {
    private Map<String, Long> duration = new HashMap<String, Long>();
}
