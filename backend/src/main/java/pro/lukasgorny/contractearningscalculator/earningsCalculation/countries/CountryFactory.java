package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.message.MessageService;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CountryFactory {

    private Map<CountryEnum, Country> countriesCache = new HashMap<>();

    @Autowired
    private MessageService messageService;

    private Function<CountryEnum, Country> createCountryFunction = (code) -> {
        switch (code) {
            case POLAND:
                return new CountryBuilder().countryEnum(CountryEnum.POLAND).currencyCode(CurrencyCode.PLN)
                                           .fixedCost(Poland.FIXED_COST).tax(Poland.TAX)
                                           .name(getTranslatedCountryName(code)).build();
            case GERMANY:
                return new CountryBuilder().countryEnum(CountryEnum.GERMANY).currencyCode(CurrencyCode.EUR)
                                           .fixedCost(Germany.FIXED_COST).tax(Germany.TAX)
                                           .name(getTranslatedCountryName(code)).build();
            case GREAT_BRITAIN:
                return new CountryBuilder().countryEnum(CountryEnum.GREAT_BRITAIN).currencyCode(CurrencyCode.GBP)
                                           .fixedCost(GreatBritain.FIXED_COST).tax(GreatBritain.TAX)
                                           .name(getTranslatedCountryName(code)).build();
            default:
                throw new IllegalArgumentException("Country not supported.");
        }
    };

    private String getTranslatedCountryName(CountryEnum code) {
        return messageService.get("country." + code.name());
    }

    Country getCountry(CountryEnum countryEnum) {
        return countriesCache.computeIfAbsent(countryEnum, createCountryFunction);
    }

}
