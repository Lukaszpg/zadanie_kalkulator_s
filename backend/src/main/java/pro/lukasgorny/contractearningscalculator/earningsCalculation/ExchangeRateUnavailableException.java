package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

public class ExchangeRateUnavailableException extends Exception {

    public ExchangeRateUnavailableException(CurrencyCode currencyCode) {
        super("Could not retrieve exchange rate for currency: " + currencyCode.name());
    }
}
