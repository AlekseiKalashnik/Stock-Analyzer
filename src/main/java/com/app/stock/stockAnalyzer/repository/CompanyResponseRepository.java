package com.app.stock.stockAnalyzer.repository;

import com.app.stock.stockAnalyzer.entity.CompanyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyResponseRepository extends JpaRepository<CompanyResponse, String> {
}
