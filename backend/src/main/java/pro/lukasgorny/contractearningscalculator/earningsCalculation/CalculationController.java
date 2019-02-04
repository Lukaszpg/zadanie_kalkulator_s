package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pro.lukasgorny.contractearningscalculator.Messages;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDto;

@RestController
public class CalculationController {

    private final Messages messages;
    private final EarningsCalculationService earningsCalculationService;

    @Autowired
    public CalculationController(Messages messages, EarningsCalculationService earningsCalculationService) {
        this.messages = messages;
        this.earningsCalculationService = earningsCalculationService;
    }

    @GetMapping("/calculate")
    public String calculate(@Valid @RequestBody CalculationDto calculationDto) {
        try {
            return earningsCalculationService.calculate(calculationDto).toString();
        } catch (ExchangeRateUnavailableException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messages.get("exchange.rate.not.available"), exception);
        }
    }
}