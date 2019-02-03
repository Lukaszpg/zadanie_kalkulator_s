package pro.lukasgorny.contractearningscalculator.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.lukasgorny.contractearningscalculator.cache.CurrencyRateCache;
import pro.lukasgorny.contractearningscalculator.domain.CurrencyCode;
import pro.lukasgorny.contractearningscalculator.dto.CurrencyDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

@Service
public class CurrencyRateFetchingService {

    private Logger logger = LoggerFactory.getLogger(CurrencyRateFetchingService.class);

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private final CurrencyRateCache currencyRateCache;

    @Autowired
    public CurrencyRateFetchingService(CurrencyRateCache currencyRateCache) {
        this.currencyRateCache = currencyRateCache;
    }

    public void getCurrencyRates() {
        Stream.of(CurrencyCode.values()).forEach(this::getRateByCurrencyCode);
    }

    private void getRateByCurrencyCode(CurrencyCode currencyCode) {
        try {
            sendRequest(currencyCode);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendRequest(CurrencyCode currencyCode) throws IOException {
        URL url = new URL(prepareUrl(currencyCode));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        getResponse(connection);
    }

    private void getResponse(HttpURLConnection connection) throws IOException {
        int statusCode = connection.getResponseCode();

        if (statusCode != HttpStatus.OK.value()) {
            logErrorResponse(connection.getErrorStream());
        } else {
            parseResponseAndAddToCache(connection.getInputStream());
        }
    }

    private void logErrorResponse(InputStream errorStream) throws IOException {
        logger.error(readInputStream(errorStream));
    }

    private String readInputStream(InputStream inputStream) throws IOException {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }

    private void parseResponseAndAddToCache(InputStream inputStream) throws IOException {
        String json = readInputStream(inputStream);
        currencyRateCache.add(new Gson().fromJson(json, CurrencyDto.class));
    }

    private String prepareUrl(CurrencyCode currencyCode) {
        return apiBaseUrl + currencyCode.name();
    }
}
