package com.app.stock.stockAnalyzer.job;

import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrintDataJob {
    private final StockService stockService;

    @Scheduled(fixedRateString = "${interval}")
    public void printData() throws InterruptedException, ExecutionException {
        log.info("begin runMethod");
        stockService.printTopFiveHighestValueStocks();
        stockService.printTopFiveTheGreatestChangePercent();
        log.info("end of submit runMethod");
    }
}
