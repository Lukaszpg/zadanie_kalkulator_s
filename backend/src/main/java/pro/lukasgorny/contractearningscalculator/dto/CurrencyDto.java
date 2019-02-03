package pro.lukasgorny.contractearningscalculator.dto;

import java.util.List;

public class CurrencyDto {

    private String currency;
    private String code;
    private List<CurrencyRateDto> rates;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CurrencyRateDto> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRateDto> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "code: " + this.code + " rate: " + this.rates.stream().findFirst().get().getRate();
    }
}
