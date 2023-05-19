package com.app.stock.stockAnalyzer;

import com.app.stock.stockAnalyzer.dto.CompanyDto;
import com.app.stock.stockAnalyzer.dto.StockDto;
import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.entity.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Mapper {

    private final ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private Company convertToCompany(CompanyDto companyDto) {
        return modelMapper.map(companyDto, Company.class);
    }

    private CompanyDto convertToCompanyDto(Company company) {
        return modelMapper.map(company, CompanyDto.class);
    }

    private Stock convertToStock(StockDto stockDto) {
        return modelMapper.map(stockDto, Stock.class);
    }

    private StockDto convertToStockDto(Stock stock) {
        return modelMapper.map(stock, StockDto.class);
    }
}
