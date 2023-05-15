package com.app.stock.stockAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String symbol;
    private String exchange;
    private String exchangeSuffix;
    private String exchangeName;
    private String exchangeSegment;
    private String exchangeSegmentName;
    private String name;
    private LocalDate date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private boolean isEnabled;
    private String figi;
    private Integer cik;
    private String lei;

    //TODO to entity

    //TODO to DTO
}
