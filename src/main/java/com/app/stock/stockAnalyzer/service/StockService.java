package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j(topic = "StockServiceLog:")
@Transactional
@RequiredArgsConstructor
public class StockService {
    private final IexApiClient iexApiClient;
    private final StockRepository stockRepository;

    public List<Stock> processStockData(List<Company> companies) {
        log.info("start processStockData()");
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

    public void printTopFiveTheGreatestChangePercent() {
        log.info("TopFiveTheGreatestChangePercent:\n");
        Map<String, Double> map = new HashMap<>();
        List<Stock> existStocksData = stockRepository.findAll();
        List<Company> freshCompaniesData = iexApiClient.getCompaniesData()
                .join()
                .stream()
                .filter(Company::isEnabled)
                .toList();
        List<Stock> freshStocksData = processStockData(freshCompaniesData);

        for (Stock exist : existStocksData) {
            for (Stock fresh : freshStocksData) {
                double percent = Math.round(Math.abs(exist.getLatestPrice() - fresh.getLatestPrice()) / fresh.getLatestPrice() * 100);
                if (percent != 0) {
                    map.put(exist.getCompanyName(), percent);
                }
            }
            map.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(5)
                    .forEach(x -> log.info("Company: " + x.getKey() +
                            " has percent diff approximately: " + x.getValue() + "%"));
        }
    }
//    TODO: get data from DB
//    TODO: use log everywhere instead of sout
}
