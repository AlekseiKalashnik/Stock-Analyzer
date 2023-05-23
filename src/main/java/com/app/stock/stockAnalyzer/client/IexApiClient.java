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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@Slf4j(topic = "IexApiClient:")
@Component
@RequiredArgsConstructor
public class IexApiClient {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    private final String HOST = "https://api.iex.cloud/";
    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    private final String GET_COMPANIES_RESPONSE = "https://api.iex.cloud/v1/data/CORE/REF_DATA?token=pk_af20fbfdc8844348922c69e10992fdcc";

    @Async
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

    @Async
    public CompletableFuture<Stock> getStock(String companySymbol) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("begin getStock()");
            ResponseEntity<Stock> response = restTemplate
                    .exchange(HOST + "v1/data/core/quote/" + companySymbol + "?token=" + TOKEN,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<>() {
                            });
            return response.getBody();
        });
    }
}
