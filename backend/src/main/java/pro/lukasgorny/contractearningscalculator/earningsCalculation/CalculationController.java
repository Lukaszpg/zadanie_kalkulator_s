package pro.lukasgorny.contractearningscalculator.earningsCalculation;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.dto.CalculationDto;

import javax.validation.Valid;

@RestController
public class CalculationController {

    private final EarningsCalculationService earningsCalculationService;

    @Autowired
    public CalculationController(EarningsCalculationService earningsCalculationService) {
        this.earningsCalculationService = earningsCalculationService;
    }

    @GetMapping("/calculate")
    public String calculate(@Valid @RequestBody CalculationDto calculationDto) {
        try {
            return earningsCalculationService.calculate(calculationDto).toString();
        } catch (ExchangeRateUnavailableException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity handleIllegalArgumentException(InvalidFormatException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
