package com.app.stock.stockAnalyzer;

import com.app.stock.stockAnalyzer.service.CompanyService;
import com.app.stock.stockAnalyzer.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@Slf4j(topic = "RunnerLog:")
public class ApplicationRunner {

    private final CompanyService companyService;
    private final StockService stockService;

    @Autowired
    public ApplicationRunner(CompanyService companyService, StockService stockService) {
        this.companyService = companyService;
        this.stockService = stockService;
    }

    @Scheduled(fixedRateString = "${interval}")
    public void runMethod() throws InterruptedException, ExecutionException {
        log.info("begin runMethod");
        companyService.saveCompanies(companyService.downloadCompaniesData().get());

        stockService.saveStocks(stockService.downloadStocksData().get());
        stockService.printTopFiveHighestValueStocks();
        stockService.printTopFiveTheGreatestChangePercent();
        log.info("end of submit runMethod");
    }
}
