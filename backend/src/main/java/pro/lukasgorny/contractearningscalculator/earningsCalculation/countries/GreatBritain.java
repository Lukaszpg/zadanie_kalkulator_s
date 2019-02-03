package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.math.BigDecimal;

public class GreatBritain extends Country {

    private static final BigDecimal TAX = BigDecimal.valueOf(0.25);
    private static final BigDecimal FIXED_COST = BigDecimal.valueOf(600);

    public GreatBritain() {
        super(TAX, FIXED_COST, CurrencyCode.GBP);
    }
}
