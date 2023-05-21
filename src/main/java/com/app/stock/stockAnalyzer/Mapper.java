package com.app.stock.stockAnalyzer;

import com.app.stock.stockAnalyzer.dto.CompanyDTO;
import com.app.stock.stockAnalyzer.dto.StockDTO;
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

    private Company convertToCompany(CompanyDTO companyDto) {
        return modelMapper.map(companyDto, Company.class);
    }

    private CompanyDTO convertToCompanyDto(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    private Stock convertToStock(StockDTO stockDto) {
        return modelMapper.map(stockDto, Stock.class);
    }

    private StockDTO convertToStockDto(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }
}
