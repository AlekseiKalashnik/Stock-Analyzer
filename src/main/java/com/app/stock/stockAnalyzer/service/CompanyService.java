package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToCompanyDTO)
                .toList();
    }

    public Page<Company> getPlantPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return companyRepository.findAll(pageable);
    }

    private Company convertToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }
    private CompanyDTO convertToCompanyDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }
}