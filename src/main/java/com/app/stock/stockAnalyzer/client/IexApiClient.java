package com.app.stock.stockAnalyzer.client;

import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j(topic = "IexApiClient:")
@RequiredArgsConstructor
public class IexApiClient {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    private final String HOST = "https://api.iex.cloud/";
    private final String TOKEN = "sk_acb1e2487637499299d22a252580d311";
    private final String GET_COMPANIES_RESPONSE = "https://api.iex.cloud/v1/data/CORE/REF_DATA?token=pk_399d40781ea3466bbea0df7436e7a128";
    private final String GET_STOCK_RESPONSE = "https://api.iex.cloud/v1/data/core/quote/aapl?token=sk_acb1e2487637499299d22a252580d311";

    public CompletableFuture<ConcurrentLinkedQueue<Company>> getCompaniesData() {
        log.info("start getCompaniesData()");
        ResponseEntity<ConcurrentLinkedQueue<Company>> response;
        response = restTemplate
                .exchange(GET_COMPANIES_RESPONSE,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        log.info("End of downloadCompaniesData()");
        return CompletableFuture.supplyAsync(response::getBody, executorService);
    }

    public CompletableFuture<List<Stock>> getStock(String companySymbol) {
        log.info("begin getStock()");
        return CompletableFuture.supplyAsync(() -> {
        ResponseEntity<List<Stock>> stock = restTemplate.
                exchange(HOST + "v1/data/core/quote/" + companySymbol + "?token="
                        + TOKEN, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                });
        log.info(String.valueOf(stock.getBody()));
        return stock.getBody();
        }, executorService);
    }

//    public List<Stock> getStock(String companySymbol) {
//        log.info("begin getStock()");
//        ResponseEntity<List<Stock>> stock = restTemplate.
//                exchange(HOST + "v1/data/core/quote/" + companySymbol + "?token="
//                        + TOKEN, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<>() {
//                });
//        log.info(String.valueOf(stock.getBody()));
//        return stock.getBody();
//    }
}
