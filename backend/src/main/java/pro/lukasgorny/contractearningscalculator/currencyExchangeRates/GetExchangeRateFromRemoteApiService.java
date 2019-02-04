package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.ExchangeRateDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.earningsCalculation.ExchangeRateUnavailableException;

@Service
public class GetExchangeRateFromRemoteApiService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private Function<ExchangeRateDto, BigDecimal> mapDtoToBigDecimal = (dto) -> BigDecimal.valueOf(dto.getRate());

    public BigDecimal getExchangeRateFromRemoteApi(CurrencyCode currencyCode) throws ExchangeRateUnavailableException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyDto> result;

        try {
            result = restTemplate.exchange(apiBaseUrl, HttpMethod.GET, prepareHeadersEntity(), CurrencyDto.class, prepareParams(currencyCode));
        } catch (RestClientException exception) {
            throw new ExchangeRateUnavailableException(currencyCode);
        }

        return result.getBody().getRates().stream().findFirst().map(mapDtoToBigDecimal).get();

    }

    private Map<String, String> prepareParams(CurrencyCode currencyCode) {
        Map<String, String> params = new HashMap<>();
        params.put("code", currencyCode.name());

        return params;
    }

    private HttpEntity<String> prepareHeadersEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>("parameters", headers);
    }
}
