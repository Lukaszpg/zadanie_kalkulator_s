package pro.lukasgorny.contractearningscalculator.dto;

import com.google.gson.annotations.SerializedName;

public class CurrencyRateDto {

    @SerializedName("mid")
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
