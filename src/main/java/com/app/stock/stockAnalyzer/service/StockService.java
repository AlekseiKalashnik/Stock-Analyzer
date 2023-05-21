package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j(topic = "StockServiceLog:")
@Transactional(readOnly = true)
public class StockService {
    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;
    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    //    private final String COMPANY_SYMBOL = "MSFT";
    //    private final String GET_STOCK_BY_SYMBOL = "https://api.iex.cloud/v1/data/core/quote/" + COMPANY_SYMBOL + "?token=" + TOKEN;
    private final String GET_STOCK_COLLECTION = "https://api.iex.cloud/v1/data/core/" +
            "stock_collection/list?collectionName=iexvolume&token=" + TOKEN;

    @Autowired
    public StockService(RestTemplateBuilder restTemplate, StockRepository stockRepository) {
        this.restTemplate = restTemplate.build();
        this.stockRepository = stockRepository;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Async
    public CompletableFuture<ConcurrentLinkedQueue<Stock>> downloadStocksData() {
        log.info("begin downloadStocksData()");
        ResponseEntity<ConcurrentLinkedQueue<Stock>> response = restTemplate
                .exchange(GET_STOCK_COLLECTION,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        log.info("end of downloadStocksData()");
        return CompletableFuture.supplyAsync(response::getBody, executorService);
    }

    public void printTopFiveHighestValueStocks() {
        System.out.println('\n' + "TopFiveHighestValueStocks:");
        stockRepository.findAll().stream()
                .sorted(Comparator.comparing(Stock::getVolume)
                        .thenComparing(Stock::getCompanyName)).limit(5)
                .forEach(x -> System.out.println("Company: " + x.getCompanyName() +
                        ",  " + '\n' + " volume: " + x.getVolume() + '\n'));
    }

    @Transactional
    public void printTopFiveTheGreatestChangePercent() throws ExecutionException, InterruptedException {
        List<Stock> oldDBData = stockRepository.findAll();
        ConcurrentLinkedQueue<Stock> freshStocksData = downloadStocksData().get();
        Map<String, Double> map = new ConcurrentHashMap<>();

        for (Stock fresh : freshStocksData) {
            for (Stock old : oldDBData) {
                double percent = Math.round(Math.abs(old.getLatestPrice() - fresh.getLatestPrice()) / fresh.getLatestPrice() * 100);
                if (percent != 0) {
                    map.put(old.getCompanyName(), percent);
                }
            }
        }
        System.out.println('\n' + "TopFiveTheGreatestChangePercent:");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(5)
                .forEach(x -> System.out.println("Company: " + x.getKey() + " has percent diff approximately: " + x.getValue() + "%"));
        System.out.println();
    }

    @Transactional
    @SneakyThrows
    public void saveStocks(Queue<Stock> stockQueue) {
        log.info("try to save stocks");
        stockRepository.saveAll(stockQueue);
        log.info("stocks have saved");
    }
//
//    @SneakyThrows
//    @Async
//    @Transactional
//    public CompletableFuture<ConcurrentLinkedQueue<Stock>> getAllStocksData() {
//        log.info("try downloadAllStocksData");
//        ConcurrentLinkedQueue<Stock> stocks;
////        for (CompanyResponse companyResponse : companyService.getExistedCompanyList()) {
//        ResponseEntity<ConcurrentLinkedQueue<Stock>> response = restTemplate
//                .exchange(GET_STOCK_COLLECTION,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<>() {
//                        });
//        log.info("try to put response to queue");
//        stocks = response.getBody();
//        //resultQueue.add(stocks.peek());
////        System.out.println(stocks);
////        }
//        log.info("end of downloadAllStocksData()");
//        return CompletableFuture.completedFuture(stocks);
//    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    @Transactional
//    public List<Stock> getStockLatestPriceListFromDB() {
//        return stockRepository.findAll().stream()
//                .sorted(Comparator.comparing(Stock::getLatestPrice))
//                .collect(Collectors.toList());
//    }
//
//    //Not finish
//    @Transactional
//    public void printTopFiveChangePercentOfCompanies() {
//        log.info("begin printTopFiveStocks()");
//        ResponseEntity<ConcurrentLinkedQueue<Stock>> response = restTemplate
//                .exchange(GET_STOCK_COLLECTION,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Stock>>() {
//                        });
//        ConcurrentLinkedQueue<Stock> stocks = response.getBody();
//        log.info("extract StockEntity");
////        System.out.println(stocks);
//        log.info("end of printTopFiveStocks()");
//    }

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

//    DONE
//    public CompletableFuture<ConcurrentLinkedQueue<Stock>> getStock(String companyName) {
//        log.info("begin getStock()");
//        ResponseEntity<ConcurrentLinkedQueue<Stock>> response = restTemplate
//                .exchange(BASE_URL + "data/core/quote/" + companyName + "?token=" + TOKEN,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Stock>>() {
//                        });
//        ConcurrentLinkedQueue<Stock> stocks = response.getBody();
//        log.info("extract StockEntity");
////        System.out.println(stocks);
//        log.info("end of getStock()");
//        if (stocks.isEmpty()) {
//            throw new NoCompaniesInCollection();
//        } else {
//            return CompletableFuture.completedFuture(stocks);
//        }
//    }
}
