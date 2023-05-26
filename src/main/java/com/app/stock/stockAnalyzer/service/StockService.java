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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final IexApiClient iexApiClient;
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    public List<Stock> processStockData(List<Company> companies) {
        List<StockDTO> stockList = companies.stream()
                .map(company -> iexApiClient.getStock(company.getSymbol()).join()).flatMap(List::stream).toList();
        return stockRepository.saveAll(stockList.stream().map(this::convertToStock).toList());

//        List<List<Stock>> stockList = companies.stream()
//                .map(company -> iexApiClient.getStock(company.getSymbol())).toList();
//        return stockList.stream().map(stockRepository::saveAll).toList();


//        log.info("start processStockData()");
//                List<Stock> stocks = companies
//                .stream()
//                .map(c -> iexApiClient
//                        .getStock(c.getSymbol()))
//                        .toList();
//        log.info("end of processStockData()");
//        return stockRepository.saveAll(stocks);
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
                .stream().map(this::convertToCompany)
//                .filter(Company::isEnabled)
                .toList();
        log.info("start calculating");
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

    private Company convertToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }

    private Stock convertToStock(StockDTO stockDTO) {
        return modelMapper.map(stockDTO, Stock.class);
    }

//    TODO: get data from DB
//    TODO: use log everywhere instead of sout
}
