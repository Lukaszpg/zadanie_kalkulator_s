package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CountryEnum {
    POLAND("POLAND"),
    GERMANY("GERMANY"),
    GREAT_BRITAIN("GREAT_BRITAIN");

    private String value;

    CountryEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CountryEnum fromValue(String text) {
        for (CountryEnum b : CountryEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
