package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.ExchangeRateUnavailableException;

@Component
public class ExchangeRatesUpdateTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExchangeRateService exchangeRateService;

    public ExchangeRatesUpdateTask(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(fixedDelayString = "${cache.refresh.delay}")
    @CacheEvict(allEntries = true, cacheNames = "exchangeRates")
    public void executeTask() {
        logger.info("Refreshing exchange rate cache.");

        Stream.of(CurrencyCode.values()).filter(currencyCode -> !CurrencyCode.PLN.equals(currencyCode)).forEach(code -> {
            try {
                exchangeRateService.getExchangeRate(code);
                logger.info("Exchange rate refreshed for: " + code.name());
            } catch (ExchangeRateUnavailableException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}
