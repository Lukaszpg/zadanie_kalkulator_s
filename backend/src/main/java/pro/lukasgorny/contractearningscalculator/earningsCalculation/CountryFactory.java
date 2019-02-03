package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import org.springframework.stereotype.Component;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Germany;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.GreatBritain;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Poland;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.enums.CountryEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CountryFactory {

    private Map<CountryEnum, Country> countriesCache = new HashMap<>();

    private Function<CountryEnum, Country> createCountryFunction = (code) -> {
        switch(code) {
            case POLAND: return new Poland();
            case GERMANY: return new Germany();
            case GREAT_BRITAIN: return new GreatBritain();
            default: throw new IllegalArgumentException("Country not supported.");
        }
    };

    public Country getCountry(CountryEnum countryEnum) {
        return countriesCache.computeIfAbsent(countryEnum, createCountryFunction);
    }

}
