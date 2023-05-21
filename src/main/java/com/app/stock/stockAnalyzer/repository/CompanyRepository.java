package com.app.stock.stockAnalyzer.repository;

import com.app.stock.stockAnalyzer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}
