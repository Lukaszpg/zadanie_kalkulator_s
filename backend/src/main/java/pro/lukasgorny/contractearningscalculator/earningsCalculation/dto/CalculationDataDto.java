package pro.lukasgorny.contractearningscalculator.earningsCalculation.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum;

public class CalculationDataDto {

    @NotNull(message = "{wrong.country.specified}")
    private CountryEnum country;

    @NotNull(message = "{daily.amount.NotNull}")
    @PositiveOrZero(message = "{daily.amount.PositiveOrZero}")
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(CountryEnum country) {
        this.country = country;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
