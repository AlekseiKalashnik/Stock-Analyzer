package com.app.stock.stockAnalyzer.repository;

import com.app.stock.stockAnalyzer.dto.StockDto;
import com.app.stock.stockAnalyzer.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
//    ConcurrentLinkedQueue<StockDto> getStockByCompanyName(String companyName);
}
