package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.Country;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final GetCountryService getCountryService;

    @Autowired
    public CountryController(GetCountryService getCountryService) {
        this.getCountryService = getCountryService;
    }

    @GetMapping("/getAll")
    public List<Country> getAll() {
        return getCountryService.getAll();
    }
}
