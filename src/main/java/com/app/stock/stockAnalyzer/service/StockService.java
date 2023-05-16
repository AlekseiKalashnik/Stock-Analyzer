package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {
    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;
}
