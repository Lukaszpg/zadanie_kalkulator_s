package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.GetExchangeRateService;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDto;

import java.math.BigDecimal;
import java.util.Optional;

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

    public BigDecimal calculate(CalculationDto calculationDto) throws ExchangeRateNotFoundException {
        Country country = countryFactory.getCountry(calculationDto.getCountryEnum());
        BigDecimal exchangeRate = getExchangeRate(country.getCurrencyCode());
        BigDecimal tax = BigDecimal.ONE.subtract(country.getTax());
        BigDecimal days = BigDecimal.valueOf(daysInMonth);
        BigDecimal overallAmount = calculationDto.getAmount().multiply(days);
        overallAmount = overallAmount.multiply(tax);
        overallAmount = overallAmount.subtract(country.getFixedCost());

        return overallAmount.multiply(exchangeRate);
    }

    private BigDecimal getExchangeRate(CurrencyCode currencyCode) throws ExchangeRateNotFoundException {
        Optional<CurrencyDto> currencyDtoOptional = getExchangeRateService.getExchangeRate(currencyCode);

        if(!currencyDtoOptional.isPresent()) {
            throw new ExchangeRateNotFoundException();
        }

        return BigDecimal.valueOf(currencyDtoOptional.get().getRates().stream().findFirst().get().getRate());
    }

}
