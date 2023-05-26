package com.app.stock.stockAnalyzer.job;

import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import com.app.stock.stockAnalyzer.service.CompanyService;
import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j(topic = "ProcessDataJob:")
@RequiredArgsConstructor
public class ProcessDataJob {
    private final CompanyService companyService;
    private final StockService stockService;

    @Scheduled(fixedRateString = "${process.stocks.data.interval}")
    public void processData() {
        log.info("start processData()");
        List<Company> savedCompanies = companyService.getCompaniesData();
        stockService.processStockData(savedCompanies);
        log.info("end of processData()");
    }
}
