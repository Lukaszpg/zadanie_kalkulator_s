package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode;

import java.math.BigDecimal;

class CountryBuilder {

    private Country country;

    public CountryBuilder() {
        country = new Country();
    }

    public CountryBuilder tax(BigDecimal tax) {
        country.setTax(tax);
        return this;
    }

    public CountryBuilder fixedCost(BigDecimal fixedCost) {
        country.setFixedCost(fixedCost);
        return this;
    }

    public CountryBuilder currencyCode(CurrencyCode currencyCode) {
        country.setCurrencyCode(currencyCode);
        return this;
    }

    public CountryBuilder countryEnum(CountryEnum countryEnum) {
        country.setCountryEnum(countryEnum);
        return this;
    }

    public CountryBuilder name(String name) {
        country.setName(name);
        return this;
    }

    public Country build() {
        return country;
    }

}
