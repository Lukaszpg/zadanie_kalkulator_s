package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.util.stream.Stream;

@Component
public class ExchangeRatesUpdateTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GetExchangeRateService getExchangeRateService;

    @Autowired
    public ExchangeRatesUpdateTask(GetExchangeRateService getExchangeRateService) {
        this.getExchangeRateService = getExchangeRateService;
    }

    @Scheduled(fixedDelayString = "${cache.refresh.delay}")
    @CacheEvict(allEntries = true, cacheNames = "exchangeRates")
    public void executeTask() {
        logger.info("Refreshing exchange rate cache.");
        Stream.of(CurrencyCode.values()).filter(currencyCode -> !CurrencyCode.PLN.equals(currencyCode)).forEach(getExchangeRateService::getExchangeRate);
    }
}
