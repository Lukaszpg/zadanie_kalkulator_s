package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.math.BigDecimal;

public abstract class Country {
    protected BigDecimal tax;
    protected BigDecimal fixedCost;
    protected CurrencyCode currencyCode;

    public Country(BigDecimal tax, BigDecimal fixedCost, CurrencyCode currencyCode) {
        this.tax = tax;
        this.fixedCost = fixedCost;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTax() {
        return this.tax;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }
}
