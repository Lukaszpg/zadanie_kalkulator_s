package pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateDto {

    @JsonProperty("mid")
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
