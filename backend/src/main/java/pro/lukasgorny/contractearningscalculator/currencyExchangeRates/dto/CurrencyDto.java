package pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto;

import java.util.List;

public class CurrencyDto {

    private List<ExchangeRateDto> rates;

    public List<ExchangeRateDto> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRateDto> rates) {
        this.rates = rates;
    }
}
