package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "CompanyServiceLog:")
@Transactional(readOnly = true)
public class CompanyService {
    private final IexApiClient iexApiClient;
    private final CompanyRepository companyRepository;

    @Async
    public List<Company> getCompaniesData() {
        List<Company> companies = iexApiClient.getCompaniesData()
                .join()
                .stream()
                .filter(Company::isEnabled)
                .toList();

        return companyRepository.saveAll(companies);
    }
}