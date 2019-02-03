package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.ExchangeRateDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GetExchangeRateService {

    private final GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService;

    @Autowired
    public GetExchangeRateService(GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService) {
        this.getExchangeRateFromRemoteApiService = getExchangeRateFromRemoteApiService;
    }

    @Cacheable(value = "exchangeRates")
    public Optional<CurrencyDto> getExchangeRate(CurrencyCode currencyCode) {
        if(CurrencyCode.PLN.equals(currencyCode)) {
            return getOptionalForPolishCurrency();
        }

        return getExchangeRateFromRemoteApiService.getExchangeRateFromRemoteApi(currencyCode);
    }

    private Optional<CurrencyDto> getOptionalForPolishCurrency() {
        CurrencyDto currencyDto = new CurrencyDto();
        ExchangeRateDto currencyExchangeRateDto = new ExchangeRateDto();
        currencyDto.setRates(new ArrayList<>());
        currencyExchangeRateDto.setRate(1.0);
        currencyDto.getRates().add(currencyExchangeRateDto);

        return Optional.of(currencyDto);
    }
}
