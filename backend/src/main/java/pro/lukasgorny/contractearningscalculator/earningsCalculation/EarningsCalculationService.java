package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.GetExchangeRateService;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.CountryFactory;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDto;

@Service
public class EarningsCalculationService {

    private final CountryFactory countryFactory;
    private final GetExchangeRateService getExchangeRateService;

    @Value("${days.in.month}")
    private Integer daysInMonth;

    @Autowired
    public EarningsCalculationService(CountryFactory countryFactory, GetExchangeRateService getExchangeRateService) {
        this.countryFactory = countryFactory;
        this.getExchangeRateService = getExchangeRateService;
    }

    public BigDecimal calculate(CalculationDto calculationDto) throws ExchangeRateUnavailableException {
        Country country = countryFactory.getCountry(calculationDto.getCountryEnum());
        BigDecimal exchangeRate = getExchangeRateService.getExchangeRate(country.getCurrencyCode());
        BigDecimal tax = BigDecimal.ONE.subtract(country.getTax());
        BigDecimal days = BigDecimal.valueOf(daysInMonth);
        BigDecimal overallAmount = calculationDto.getAmount().multiply(days);
        overallAmount = overallAmount.multiply(tax);
        overallAmount = overallAmount.subtract(country.getFixedCost());

        return overallAmount.multiply(exchangeRate);
    }

}
