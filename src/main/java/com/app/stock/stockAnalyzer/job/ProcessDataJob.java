package com.app.stock.stockAnalyzer.job;

import com.app.stock.stockAnalyzer.service.CompanyService;
import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "ProcessDataJob:")
@RequiredArgsConstructor
public class ProcessDataJob {
    private final CompanyService companyService;
    private final StockService stockService;

    @Scheduled(fixedRateString = "${process.stocks.data.interval}")
    public void processData() {
        stockService.processStockData(companyService.getCompaniesData().join());
    }
}
