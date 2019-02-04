package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.Messages;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CountryFactory {

    private Map<CountryEnum, Country> countriesCache = new HashMap<>();

    @Autowired
    private Messages messages;

    private Function<CountryEnum, Country> createCountryFunction = (code) -> {
        switch (code) {
            case POLAND:
                return new CountryBuilder().countryEnum(CountryEnum.POLAND).currencyCode(CurrencyCode.PLN)
                        .fixedCost(Poland.FIXED_COST).tax(Poland.TAX)
                        .name(messages.get("country." + CountryEnum.POLAND.name())).build();
            case GERMANY:
                return new CountryBuilder().countryEnum(CountryEnum.GERMANY).currencyCode(CurrencyCode.EUR)
                        .fixedCost(Germany.FIXED_COST).tax(Germany.TAX)
                        .name(messages.get("country." + CountryEnum.GERMANY.name())).build();
            case GREAT_BRITAIN:
                return new CountryBuilder().countryEnum(CountryEnum.GREAT_BRITAIN).currencyCode(CurrencyCode.GBP)
                        .fixedCost(GreatBritain.FIXED_COST).tax(GreatBritain.TAX)
                        .name(messages.get("country." + CountryEnum.GREAT_BRITAIN.name())).build();
            default:
                throw new IllegalArgumentException("Country not supported.");
        }
    };

    Country getCountry(CountryEnum countryEnum) {
        return countriesCache.computeIfAbsent(countryEnum, createCountryFunction);
    }

}
