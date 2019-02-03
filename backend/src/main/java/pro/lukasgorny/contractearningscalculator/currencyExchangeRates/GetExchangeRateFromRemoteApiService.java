package pro.lukasgorny.contractearningscalculator.currencyExchangeRates;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.dto.CurrencyDto;
import pro.lukasgorny.contractearningscalculator.currencyExchangeRates.enums.CurrencyCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GetExchangeRateFromRemoteApiService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public Optional<CurrencyDto> getExchangeRateFromRemoteApi(CurrencyCode currencyCode) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyDto> result = restTemplate.exchange(apiBaseUrl, HttpMethod.GET, prepareHeadersEntity(), CurrencyDto.class, prepareParams(currencyCode));

        return Optional.ofNullable(result.getBody());
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
