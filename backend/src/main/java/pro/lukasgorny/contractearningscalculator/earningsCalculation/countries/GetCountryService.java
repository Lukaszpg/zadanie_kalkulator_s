package pro.lukasgorny.contractearningscalculator.earningsCalculation.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GetCountryService {

    private final CountryFactory countryFactory;

    @Autowired
    public GetCountryService(CountryFactory countryFactory) {
        this.countryFactory = countryFactory;
    }

    public List<Country> getAll() {
        return Stream.of(CountryEnum.values()).map(countryFactory::getCountry).collect(Collectors.toList());
    }

    public Country getCountryByCountryEnum(CountryEnum countryEnum) {
        return countryFactory.getCountry(countryEnum);
    }
}