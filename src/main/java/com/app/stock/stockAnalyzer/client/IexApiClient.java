package com.app.stock.stockAnalyzer.client;

import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j(topic = "IexApiClient:")
@RequiredArgsConstructor
public class IexApiClient {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    @Value("${iexapi.hcom/app/stock/stockAnalyzer/client/IexApiClient.java:25ost}")
    private String HOST;
    @Value("${iexapi.token}")
    private String TOKEN;
    @Value("${iexapi.companies_request}")
    private String GET_COMPANIES_REQUEST;
    @Value("${iexapi.stock_request}")
    private String GET_STOCK_REQUEST;

    public CompletableFuture<Queue<CompanyDTO>> getCompaniesData() {
        log.info("start getCompaniesData()");
        return CompletableFuture.supplyAsync(() -> {
            ResponseEntity<ConcurrentLinkedQueue<CompanyDTO>> response;
            response = restTemplate
                    .exchange(HOST + GET_COMPANIES_REQUEST + TOKEN,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<>() {
                            });
            log.info("end of downloadCompaniesData()");
            return response.getBody();
        }, executorService);
    }

    public CompletableFuture<Queue<StockDTO>> getStock(String companySymbol) {
        log.info("begin getStock()");
        return CompletableFuture.supplyAsync(() -> {
            ResponseEntity<ConcurrentLinkedQueue<StockDTO>> stock = restTemplate.
                    exchange(HOST + GET_STOCK_REQUEST + companySymbol + TOKEN,
                            HttpMethod.GET, null,
                            new ParameterizedTypeReference<>() {
                            });
            log.info("end of getStock()");
            return stock.getBody();
        }, executorService);
    }
    //может создать в каждом методе по объекту Future и затем их где-то thenCompose?
}
