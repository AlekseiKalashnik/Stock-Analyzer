package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = "CompanyServiceLog:")
@RequiredArgsConstructor
public class CompanyService {
    private final IexApiClient iexApiClient;
    private final CompanyRepository companyRepository;

    public List<Company> getCompaniesData() {
        log.info("start getCompaniesData()");
        List<Company> companies = iexApiClient.getCompaniesData()
                .join()
                .stream()
//                .filter(Company::isEnabled)
                .toList();
        log.info(companies.toString());
        log.info("end of getCompaniesData()");
        return companyRepository.saveAll(companies);
    }
}