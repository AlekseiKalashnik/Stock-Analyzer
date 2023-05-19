package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Slf4j(topic = "StockServiceLog:")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {
    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;

    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    private final String COMPANY = "MSFT";
    private final String BASE_URL = "https://api.iex.cloud/v1/";
    private final String GET_STOCK_BY_SYMBOL = BASE_URL + "data/core/quote/" + COMPANY + "?token=" + TOKEN;

    @Async
    public CompletableFuture<Queue<Stock>> getStockQueue(String companyName) {
        log.info("begin getStockQueue()");
        ResponseEntity<ConcurrentLinkedQueue<Stock>> response = restTemplate
                .exchange(BASE_URL + "data/core/quote/" + companyName + "?token=" + TOKEN,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Stock>>() {
                        });
        ConcurrentLinkedQueue<Stock> stocks = response.getBody();
        log.info("extract StockEntity");
//        System.out.println(stocks);
        log.info("end of getCompanyQueue()");
        return CompletableFuture.completedFuture(stocks);
    }

    @SneakyThrows
    @Transactional
    public void saveStock() {
        stockRepository.saveAll(getStockQueue(COMPANY).get());
        log.info("stock has saved");
    }

//    public List<Stock> getStock(String companyName) {
//        System.out.println("Start");
//        ResponseEntity<List<Stock>> response =
//                restTemplate
//                        .exchange(BASE_URL + "data/core/quote/" + companyName + "?token=" + TOKEN,
//                                HttpMethod.GET,
//                                null,
//                                new ParameterizedTypeReference<List<Stock>>() {
//                                });
//        List<Stock> stocks = response
//                .getBody()
//                .stream()
//                .collect(Collectors.toList());
//        System.out.println("TEST");
//        System.out.println(stocks);
//        System.out.println("End");
//        return stocks;
//    }
}
