package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.ExchangeRateService;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.CountryService;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDataDto;

@Service
public class EarningsCalculationService {

    private final CountryService countryService;
    private final ExchangeRateService exchangeRateService;

    @Value("${days.in.month}")
    private Integer daysInMonth;

    @Autowired
    public EarningsCalculationService(CountryService countryService, ExchangeRateService exchangeRateService) {
        this.countryService = countryService;
        this.exchangeRateService = exchangeRateService;
    }

    public BigDecimal calculate(CalculationDataDto calculationDataDto) throws ExchangeRateUnavailableException {
        Country country = countryService.getCountryByCountryEnum(calculationDataDto.getCountry());
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(country.getCurrencyCode());
        BigDecimal tax = BigDecimal.ONE.subtract(country.getTax());
        BigDecimal days = BigDecimal.valueOf(daysInMonth);
        BigDecimal overallAmount = calculationDataDto.getAmount().multiply(days);
        overallAmount = overallAmount.multiply(tax);
        overallAmount = overallAmount.subtract(country.getFixedCost());

        return overallAmount.multiply(exchangeRate).setScale(2, RoundingMode.CEILING);
    }

}
