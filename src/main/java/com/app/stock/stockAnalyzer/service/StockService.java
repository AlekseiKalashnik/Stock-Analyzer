package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
public class StockService {
    private final IexApiClient iexApiClient;
    private final StockRepository stockRepository;

    public List<Stock> processStockData(List<Company> companies) {
        List<Stock> stocks = companies.stream()
                .map(c -> iexApiClient.getStock(c.getSymbol())).toList()
                .stream()
                .map(CompletableFuture::join)
                .toList();
        return stockRepository.saveAll(stocks);
    }

    public void printTopFiveHighestValueStocks() {
        log.info("TopFiveHighestValueStocks:\n");
        stockRepository.findAll().stream()
                .sorted(Comparator.comparing(Stock::getVolume)
                        .thenComparing(Stock::getCompanyName)).limit(5)
                .forEach(x -> log.info("Company: " + x.getCompanyName() +
                        ",  " + '\n' + " volume: " + x.getVolume() + '\n'));
    }

    @Transactional
    public void printTopFiveTheGreatestChangePercent() throws ExecutionException, InterruptedException {
        //TODO: get data from DB
        //TODO: use log everywhere instead of sout
    }
}
