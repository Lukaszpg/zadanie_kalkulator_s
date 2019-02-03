package pro.lukasgorny.contractearningscalculator.cache;

import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.domain.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.dto.CurrencyRateDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyRateCache {

    private Map<CurrencyCode, Double> cache = new HashMap<>();

    public void add(CurrencyDto currencyDto) {
        cache.put(CurrencyCode.valueOf(currencyDto.getCode()), getRate(currencyDto));
    }

    public Double getRateByCurrencyCode(CurrencyCode currencyCode) {
        return cache.get(currencyCode);
    }

    private Double getRate(CurrencyDto currencyDto) {
        return currencyDto.getRates().stream().findFirst().map(CurrencyRateDto::getRate).orElse(0.0);
    }
}

