package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.client.IexApiClient;
import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private IexApiClient iexApiClient;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void shouldGetAndSaveCompanies_getCompaniesDataTest() {
        Company company = Company.builder().symbol("aapl").cik("f").build();
        CompanyDTO companyDTO = CompanyDTO.builder().symbol("fb").cik("t").build();
        List<Company> companies = List.of(
                company
        );

        List<CompanyDTO> actualResult = iexApiClient.getCompaniesData().join().stream().toList();
    }

}