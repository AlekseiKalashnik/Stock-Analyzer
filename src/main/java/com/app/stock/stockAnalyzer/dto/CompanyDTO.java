package com.app.stock.stockAnalyzer.dto;

import com.app.stock.stockAnalyzer.entity.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    String symbol;
    String exchange;
    String exchangeSuffix;
    String exchangeName;
    String exchangeSegment;
    String exchangeSegmentName;
    String name;
    String date;
    String type;
    String iexId;
    String region;
    @Enumerated(EnumType.STRING)
    Currency currency;
    boolean isEnabled;
    String figi;
    String cik;
    String lei;
}
