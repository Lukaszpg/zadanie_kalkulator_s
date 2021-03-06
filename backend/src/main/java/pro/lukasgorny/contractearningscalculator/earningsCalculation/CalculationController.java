package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDataDto;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationResponseDto;
import pro.lukasgorny.contractearningscalculator.message.MessageService;

@RestController
public class CalculationController {

    private final MessageService messageService;
    private final EarningsCalculationService earningsCalculationService;

    @Autowired
    public CalculationController(MessageService messageService, EarningsCalculationService earningsCalculationService) {
        this.messageService = messageService;
        this.earningsCalculationService = earningsCalculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponseDto> calculate(@Valid @RequestBody CalculationDataDto calculationDataDto) {
        try {
            return ResponseEntity.ok(earningsCalculationService.calculate(calculationDataDto));
        } catch (ExchangeRateUnavailableException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("exchange.rate.not.available"), exception);
        }
    }
}