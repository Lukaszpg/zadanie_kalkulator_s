package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.math.BigDecimal;

public class Germany extends Country {

    private static final BigDecimal TAX = BigDecimal.valueOf(0.2);
    private static final BigDecimal FIXED_COST = BigDecimal.valueOf(800);

    public Germany() {
        super(TAX, FIXED_COST, CurrencyCode.EUR);
    }
}
