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
        def code = CurrencyCode.PLN
        when:
        def result = exchangeRateService.getExchangeRate(code)
        then:
        result == BigDecimal.ONE
    }

    def "when CurrencyCode.EUR is given then non-zero non-null BigDecimal is returned"() {
        given:
        def code = CurrencyCode.EUR
        when:
        def result = exchangeRateService.getExchangeRate(code)
        then:
        result != null && result > BigDecimal.ZERO
    }
}
