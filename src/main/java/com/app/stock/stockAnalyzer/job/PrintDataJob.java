package com.app.stock.stockAnalyzer.job;

import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j(topic = "PrintDataJob:")
@Component
@RequiredArgsConstructor
public class PrintDataJob {
    private final StockService stockService;

    @Scheduled(fixedRateString = "${process.stocks.data.interval}")
    public void printData() {
        log.info("start printData()");
        stockService.printTopFiveHighestValueStocks();
        stockService.printTopFiveTheGreatestChangePercent();
    }
}
