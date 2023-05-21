package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_response")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    @Id
    @Column
    String symbol;
    @Column
    String exchange;
    @Column
    String exchangeSuffix;
    @Column
    String exchangeName;
    @Column
    String exchangeSegment;
    @Column
    String exchangeSegmentName;
    @Column
    String name;
    @Column
    String date;
    @Column
    String type;
    @Column
    String iexId;
    @Column
    String region;
    @Enumerated(EnumType.STRING)
    @Column
    Currency currency;
    @Column
    boolean isEnabled;
    @Column
    String figi;
    @Column
    String cik;
    @Column
    String lei;
}
