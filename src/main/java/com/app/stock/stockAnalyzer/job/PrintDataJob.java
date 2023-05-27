package com.app.stock.stockAnalyzer.job;

import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j(topic = "PrintDataJob:")
@RequiredArgsConstructor
@Transactional
public class PrintDataJob {
    private final StockService stockService;

    @Scheduled(fixedRateString = "${process.stocks.data.interval}")
    public void printData() {
        stockService.printTopFiveHighestValueStocks();
        stockService.printTopFiveTheGreatestChangePercent();
    }
}
