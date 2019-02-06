package pro.lukasgorny.contractearningscalculator.earningsCalculation.dto;

import java.math.BigDecimal;

public class CalculationResponseDto {
    private BigDecimal result;

    public CalculationResponseDto(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

}
