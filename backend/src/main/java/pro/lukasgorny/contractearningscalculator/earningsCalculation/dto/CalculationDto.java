package pro.lukasgorny.contractearningscalculator.earningsCalculation.dto;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.CountryEnum;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class CalculationDto {

    private CountryEnum countryEnum;

    @PositiveOrZero
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public CountryEnum getCountryEnum() {
        return countryEnum;
    }
}
