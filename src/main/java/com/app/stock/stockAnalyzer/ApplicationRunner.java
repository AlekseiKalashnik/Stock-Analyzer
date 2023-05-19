package com.app.stock.stockAnalyzer;

import com.app.stock.stockAnalyzer.service.CompanyService;
import com.app.stock.stockAnalyzer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@EnableScheduling
public class ApplicationRunner {

    private final CompanyService companyService;
    private final StockService stockService;

    @Autowired
    public ApplicationRunner(CompanyService companyService, StockService stockService) {
        this.companyService = companyService;
        this.stockService = stockService;
    }

    @Scheduled(fixedRate = 5000)
    public void runMethod() {
        System.out.println("Begin run method");
        companyService.saveCompany();
        stockService.saveStock();
    }
}
