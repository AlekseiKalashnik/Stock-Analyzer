package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.dto.StockDTO;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final IexApiClient iexApiClient;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ExecutorService executorService;
    @Autowired
    @Lazy
    private StockService selfService;

    @Transactional
    public CompletableFuture<List<Stock>> processStockData(List<Company> companies) {
        return CompletableFuture.supplyAsync(() -> {
        List<Stock> stockList = companies.stream()
                .map(company -> iexApiClient.getStock(company.getSymbol()))
                .map(CompletableFuture::join)
                .map(Queue::element)
                .map(this::convertToStock)
                .toList();
        return stockRepository.saveAll(stockList);
        }, executorService);
    }

    public void printTopFiveHighestValueStocks() {
        log.info("TopFiveHighestValueStocks:\n");
        stockRepository.findAll().stream().filter(Objects::nonNull).limit(5)
                .sorted(Comparator.comparing(Stock::getPreviousVolume).reversed()
                        .thenComparing(Stock::getCompanyName))
                .forEach(x -> log.info("Company: " + x.getCompanyName() +
                        ",  " + '\n' + " volume: " + x.getPreviousVolume() + '\n'));
    }

    public void printTopFiveTheGreatestChangePercent() {
        log.info("TopFiveTheGreatestChangePercent:\n");
        List<Company> companiesData = companyRepository.findAll();
        List<Stock> existStocksData = stockRepository.findAll();
        List<Stock> freshStocksData = selfService.processStockData(companiesData).join();

        Map<String, Double> mapExistStocks = existStocksData.stream()
                .collect(Collectors.toMap(Stock::getSymbol, Stock::getLatestPrice));
        Map<String, Double> mapFreshStocks = freshStocksData.stream()
                .collect(Collectors.toMap(Stock::getSymbol, Stock::getLatestPrice));

        log.info("start calculating");
        calculateStokeLatestPrice(mapExistStocks, mapFreshStocks)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(5)
                .forEach(x -> log.info('\n' + "Company: " + x.getKey() + '\n' +
                        " has percent diff approximately: " + x.getValue() + "%"));
    }

    public Map<String, Double> calculateStokeLatestPrice(Map<String, Double> exist, Map<String, Double> fresh) {
        Map<String, Double> resultMap = new HashMap<>();
        for (String symbol : exist.keySet()) {
            double percent = Math.round(Math.abs(exist.get(symbol) - fresh.get(symbol)) / fresh.get(symbol) * 100);
            if (percent != 0) {
                resultMap.put(symbol, percent);
            }
        }
        return resultMap;
    }

    private Stock convertToStock(StockDTO stockDTO) {
        return modelMapper.map(stockDTO, Stock.class);
    }
}
