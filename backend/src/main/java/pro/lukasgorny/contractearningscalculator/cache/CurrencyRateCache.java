package pro.lukasgorny.contractearningscalculator.cache;

import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.domain.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.dto.CurrencyRateDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Optional<CurrencyRateDto> rateOptional = currencyDto.getRates().stream().findFirst();

        if(rateOptional.isPresent()) {
            return rateOptional.get().getRate();
        }

        return 0.0;
    }
}

