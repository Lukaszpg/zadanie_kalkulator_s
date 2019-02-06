package pro.lukasgorny.contractearningscalculator.tests

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pro.lukasgorny.contractearningscalculator.earningsCalculation.CalculationController
import pro.lukasgorny.contractearningscalculator.earningsCalculation.CountryEnum
import pro.lukasgorny.contractearningscalculator.earningsCalculation.EarningsCalculationService
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDataDto
import pro.lukasgorny.contractearningscalculator.message.MessageService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CalculationController.class)
class CalculationControllerTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @SpringBean
    EarningsCalculationService earningsCalculationService = Stub()

    @SpringBean
    MessageService messageService = Mock()

    @Autowired
    ObjectMapper objectMapper

    def "given calculation dto when calculating result should return ok status"() {
        given:
            def calculationDto = new CalculationDataDto()
            calculationDto.setAmount(BigDecimal.valueOf(200))
            calculationDto.setCountry(CountryEnum.GERMANY)
        when:
            def result = mockMvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(calculationDto)))
        then:
            result.andExpect(status().is2xxSuccessful())
    }
}
