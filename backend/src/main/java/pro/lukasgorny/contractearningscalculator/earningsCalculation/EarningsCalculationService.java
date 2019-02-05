package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.GetExchangeRateService;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.GetCountryService;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDataDto;

@Service
public class EarningsCalculationService {

    private final GetCountryService getCountryService;
    private final GetExchangeRateService getExchangeRateService;

    @Value("${days.in.month}")
    private Integer daysInMonth;

    @Autowired
    public EarningsCalculationService(GetCountryService getCountryService, GetExchangeRateService getExchangeRateService) {
        this.getCountryService = getCountryService;
        this.getExchangeRateService = getExchangeRateService;
    }

    public BigDecimal calculate(CalculationDataDto calculationDataDto) throws ExchangeRateUnavailableException {
        Country country = getCountryService.getCountryByCountryEnum(calculationDataDto.getCountry());
        BigDecimal exchangeRate = getExchangeRateService.getExchangeRate(country.getCurrencyCode());
        BigDecimal tax = BigDecimal.ONE.subtract(country.getTax());
        BigDecimal days = BigDecimal.valueOf(daysInMonth);
        BigDecimal overallAmount = calculationDataDto.getAmount().multiply(days);
        overallAmount = overallAmount.multiply(tax);
        overallAmount = overallAmount.subtract(country.getFixedCost());

        return overallAmount.multiply(exchangeRate);
    }

}
