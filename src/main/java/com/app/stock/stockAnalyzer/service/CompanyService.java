package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j(topic = "CompanyServiceLog:")
@RequiredArgsConstructor
public class CompanyService {
    private final IexApiClient iexApiClient;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Transactional
    public CompletableFuture<List<Company>> getCompaniesData() {
        log.info("start getCompaniesData()");
        return CompletableFuture.supplyAsync(() -> {
            List<Company> companies = iexApiClient.getCompaniesData()
                    .join()
                    .stream()
                    .map(this::convertToCompany).limit(20)
                    .toList();
            return companyRepository.saveAll(companies);
        });
    }

    private Company convertToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }
}