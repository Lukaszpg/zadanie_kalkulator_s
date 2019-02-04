package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.ExchangeRateUnavailableException;

@Service
public class GetExchangeRateService {

    private final GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService;

    @Autowired
    public GetExchangeRateService(GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService) {
        this.getExchangeRateFromRemoteApiService = getExchangeRateFromRemoteApiService;
    }

    @Cacheable(value = "exchangeRates")
    public BigDecimal getExchangeRate(CurrencyCode currencyCode) throws ExchangeRateUnavailableException {
        if(CurrencyCode.PLN.equals(currencyCode)) {
            return BigDecimal.ONE;
        }

        return getExchangeRateFromRemoteApiService.getExchangeRateFromRemoteApi(currencyCode);
    }
}
