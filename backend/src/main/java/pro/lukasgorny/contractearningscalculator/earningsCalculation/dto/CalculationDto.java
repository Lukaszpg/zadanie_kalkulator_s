package pro.lukasgorny.contractearningscalculator.earningsCalculation.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum;

public class CalculationDto {

    @NotNull(message = "{wrong.country.specified}")
    private CountryEnum countryEnum;

    @PositiveOrZero(message = "{daily.amount.error}")
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public CountryEnum getCountryEnum() {
        return countryEnum;
    }
}
