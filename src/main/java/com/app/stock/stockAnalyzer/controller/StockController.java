package com.app.stock.stockAnalyzer.controller;

import com.app.stock.stockAnalyzer.dto.StockDTO;
import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/stocks/{pageNumber}/{pageSize}")
    public List<Stock> getAllStocksByPage(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        Page<Stock> data = stockService.getPlantPage(pageNumber, pageSize);
        return data.getContent();
    }
}
