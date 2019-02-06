package pro.lukasgorny.contractearningscalculator.tests

import org.springframework.boot.test.context.SpringBootTest
import pro.lukasgorny.contractearningscalculator.Application
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.CurrencyCode
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.ExchangeRateService
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.GetExchangeRateFromRemoteApiService
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
class ExchangeRateServiceTest extends Specification {

    private GetExchangeRateFromRemoteApiService remoteApiService = Stub()
    private ExchangeRateService exchangeRateService = new ExchangeRateService(remoteApiService)

    def "when CurrencyCode.PLN is given then BigDecimal.ONE should be returned"() {
        given:
        def code = CurrencyCode.PLN
        when:
        def result = exchangeRateService.getExchangeRate(code)
        then:
        result == BigDecimal.ONE
    }

    def "when CurrencyCode.EUR is given then non-zero non-null BigDecimal should be returned"() {
        given:
        def code = CurrencyCode.EUR
        and:
        remoteApiService.getExchangeRateFromRemoteApi(code) >> 1
        when:
        def result = exchangeRateService.getExchangeRate(code)
        then:
        result != null && result > BigDecimal.ZERO
    }
}
