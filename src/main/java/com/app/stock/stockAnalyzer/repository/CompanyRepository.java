package com.app.stock.stockAnalyzer.repository;

import com.app.stock.stockAnalyzer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
