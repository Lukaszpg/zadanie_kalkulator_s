import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pro.lukasgorny.contractearningscalculator.Application
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.ExchangeRateService
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
class ExchangeRateServiceTest extends Specification {

    @Autowired
    private ExchangeRateService exchangeRateService

    def "when CurrencyCode.PLN is given then BigDecimal.ONE is returned"() {
        given:
        CurrencyCode code = CurrencyCode.PLN
        when:
        BigDecimal result = exchangeRateService.getExchangeRate(code)
        then:
        result == BigDecimal.ONE
    }
}
