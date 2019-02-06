package pro.lukasgorny.contractearningscalculator.tests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pro.lukasgorny.contractearningscalculator.Application
import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum
import pro.lukasgorny.contractearningscalculator.earningsCalculation.countries.CountryService
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
class CountryServiceTest extends Specification {

    @Autowired
    private CountryService countryService

    def "when Germany enum is given then correct Germany object should be returned"() {
        given:
        def anEnum = CountryEnum.GERMANY
        when:
        def result = countryService.getCountryByCountryEnum(anEnum)
        then:
        CountryEnum.GERMANY == result.countryEnum
    }

    def "when get all is called then not null not zero size list of country should be returned"() {
        when:
        def result = countryService.getAll()
        then:
        result != null && result.size() > 0
    }
}
