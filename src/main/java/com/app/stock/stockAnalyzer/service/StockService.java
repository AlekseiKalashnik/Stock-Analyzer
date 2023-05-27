package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.dto.StockDTO;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final IexApiClient iexApiClient;
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;
    @Autowired
    @Lazy
    private StockService selfService;

    @Transactional
    public List<Stock> processStockData(List<Company> companies) {
        return stockRepository.saveAll(companies.stream()
                .map(company -> iexApiClient
                        .getStock(company.getSymbol()).join())
                .flatMap(Queue::stream)
                .toList()
                .stream().limit(30)
                .map(this::convertToStock)
                .toList());
    }

    public void printTopFiveHighestValueStocks() {
        log.info("TopFiveHighestValueStocks:\n");
        stockRepository.findAll().stream()
                .sorted(Comparator.comparing(Stock::getVolume).reversed()
                        .thenComparing(Stock::getCompanyName)).limit(5)
                .forEach(x -> log.info("Company: " + x.getCompanyName() +
                        ",  " + '\n' + " volume: " + x.getVolume() + '\n'));
    }

    public void printTopFiveTheGreatestChangePercent() {
        log.info("TopFiveTheGreatestChangePercent:\n");
        List<Company> freshCompaniesData = iexApiClient.getCompaniesData()
                .join()
                .stream()
                .map(this::convertToCompany)
                .toList();
        List<Stock> existStocksData = stockRepository.findAll();
        List<Stock> freshStocksData = selfService.processStockData(freshCompaniesData);

        Map<String, Double> mapExistStocks = existStocksData.stream().collect(Collectors.toMap(Stock::getSymbol, Stock::getLatestPrice));
        Map<String, Double> mapFreshStocks = freshStocksData.stream().collect(Collectors.toMap(Stock::getSymbol, Stock::getLatestPrice));
        log.info("start calculating");

        calculateStokeLatestPrice(mapExistStocks, mapFreshStocks)
                .entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(5)
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

    private Company convertToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }

    private Stock convertToStock(StockDTO stockDTO) {
        return modelMapper.map(stockDTO, Stock.class);
    }

    //    TODO: get data from DB
//    TODO: use log everywhere instead of sout
}
