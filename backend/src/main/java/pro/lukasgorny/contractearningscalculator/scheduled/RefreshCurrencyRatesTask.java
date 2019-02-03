package pro.lukasgorny.contractearningscalculator.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.service.CurrencyRateFetchingService;

@Component
public class RefreshCurrencyRatesTask {

    private final CurrencyRateFetchingService currencyRateFetchingService;

    @Autowired
    public RefreshCurrencyRatesTask(CurrencyRateFetchingService currencyRateFetchingService) {
        this.currencyRateFetchingService = currencyRateFetchingService;
    }

    @Scheduled(fixedDelay = 3600000)
    public void executeTask() {
        currencyRateFetchingService.getCurrencyRates();
    }
}
