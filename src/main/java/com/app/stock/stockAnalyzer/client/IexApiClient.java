package com.app.stock.stockAnalyzer.client;

import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
@RequiredArgsConstructor
public class IexApiClient {

    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    private final String GET_STOCK_COLLECTION = "https://api.iex.cloud/v1/data/core/" +
            "stock_collection/list?collectionName=iexvolume&token=" + TOKEN;
    private final String GET_COMPANY_RESPONSE = "https://api.iex.cloud" + "/ref-data/symbols?" + "token=" + TOKEN;

    @Async
    public CompletableFuture<ConcurrentLinkedQueue<Company>> getCompaniesData() {
        log.info("begin getCompaniesData()");
        ResponseEntity<ConcurrentLinkedQueue<Company>> response;
        response = restTemplate
                .exchange(GET_COMPANY_RESPONSE,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        log.info("End of getCompaniesData()");
        return CompletableFuture.supplyAsync(response::getBody, executorService);
    }

    public CompletableFuture<Stock> getStock(String companyName) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("begin getStock()");
            ResponseEntity<Stock> response = restTemplate
                    .exchange("BASE_URL" + "data/core/quote/" + companyName + "?token=" + TOKEN,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<>() {
                            });
            return response.getBody();
        });
    }
}
