package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyServiceTest {
    private AutoCloseable autoCloseable;
    @Autowired
    private CompanyRepository testRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldGetAndSaveCompanies_getCompaniesDataTest() {
        //given
        List<Company> companies = List.of(
                Company.builder().symbol("aapl").cik("f").build(),
                Company.builder().symbol("fb").cik("t").build());

        //when
        List<Company> resultList = testRepository.saveAll(companies);
        Company companyDB = resultList.get(0);

        //then
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(companyDB.getSymbol()).isEqualTo("aapl");
        assertThat(companyDB.getCik()).isEqualTo("f");
    }
}