package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.Stock;
import com.app.stock.stockAnalyzer.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StockServiceTest {
    private AutoCloseable autoCloseable;
    @Autowired
    private StockRepository testRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldGetAndSaveStocks_processStockData() {
        //given
        List<Stock> stocks = List.of(Stock.builder().symbol("aapl").volume(BigInteger.valueOf(222)).extendedChange(1.4).build(), Stock.builder().symbol("fb").volume(BigInteger.valueOf(1000)).extendedChange(7000.50).build());

        //when
        List<Stock> resultList = testRepository.saveAll(stocks);

        //then
        assertThat(resultList).isNotEmpty();
        assertThat(resultList.size()).isEqualTo(2);
    }
}