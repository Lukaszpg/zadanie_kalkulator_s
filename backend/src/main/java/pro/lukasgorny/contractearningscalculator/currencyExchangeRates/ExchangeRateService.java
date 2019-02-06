package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.ExchangeRateUnavailableException;

@Service
public class ExchangeRateService {

    private final GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService;

    @Autowired
    public ExchangeRateService(GetExchangeRateFromRemoteApiService getExchangeRateFromRemoteApiService) {
        this.getExchangeRateFromRemoteApiService = getExchangeRateFromRemoteApiService;
    }

    @Cacheable(value = "exchangeRates")
    public BigDecimal getExchangeRate(CurrencyCode currencyCode) throws ExchangeRateUnavailableException {
        return CurrencyCode.PLN.equals(currencyCode)
                ? BigDecimal.ONE
                : getExchangeRateFromRemoteApiService.getExchangeRateFromRemoteApi(currencyCode);
    }
}
