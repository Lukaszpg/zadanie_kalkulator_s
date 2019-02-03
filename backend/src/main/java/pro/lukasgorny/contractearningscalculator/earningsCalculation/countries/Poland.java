package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.math.BigDecimal;

public class Poland extends Country {

    private static final BigDecimal TAX = BigDecimal.valueOf(0.19);
    private static final BigDecimal FIXED_COST = BigDecimal.valueOf(1200);

    public Poland() {
        super(TAX, FIXED_COST, CurrencyCode.PLN);
    }
}
