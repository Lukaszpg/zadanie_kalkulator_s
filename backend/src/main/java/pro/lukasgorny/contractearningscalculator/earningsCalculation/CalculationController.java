package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pro.lukasgorny.contractearningscalculator.Messages;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDataDto;

@RestController
public class CalculationController {

    private final Messages messages;
    private final EarningsCalculationService earningsCalculationService;

    @Autowired
    public CalculationController(Messages messages, EarningsCalculationService earningsCalculationService) {
        this.messages = messages;
        this.earningsCalculationService = earningsCalculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(@Valid @RequestBody CalculationDataDto calculationDataDto) {
        try {
            return ResponseEntity.ok(earningsCalculationService.calculate(calculationDataDto));
        } catch (ExchangeRateUnavailableException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messages.get("exchange.rate.not.available"), exception);
        }
    }
}