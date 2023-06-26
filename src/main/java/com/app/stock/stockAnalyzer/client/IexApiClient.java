package com.app.stock.stockAnalyzer.client;

import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.dto.StockDTO;
import com.fasterxml.jackson.datatype.jdk8.Jdk8UnwrappingOptionalBeanPropertyWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j(topic = "IexApiClient:")
@RequiredArgsConstructor
public class IexApiClient {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    @Value("${iexapi.host}")
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
            ResponseEntity<ConcurrentLinkedQueue<StockDTO>> stock;
                stock = restTemplate.
                        exchange(HOST + GET_STOCK_REQUEST + companySymbol + TOKEN,
                                HttpMethod.GET, null,
                                new ParameterizedTypeReference<>() {
                                });
                log.info("end of getStock()");
            return stock.getBody();
        }, executorService);
    }
}
