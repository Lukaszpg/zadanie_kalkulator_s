package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum;

import java.math.BigDecimal;

public class Country {
    protected BigDecimal tax;
    protected BigDecimal fixedCost;
    protected CurrencyCode currencyCode;
    protected CountryEnum countryEnum;
    protected String name;

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(BigDecimal fixedCost) {
        this.fixedCost = fixedCost;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CountryEnum getCountryEnum() {
        return countryEnum;
    }

    public void setCountryEnum(CountryEnum countryEnum) {
        this.countryEnum = countryEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
