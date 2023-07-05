package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @Column
    private String symbol;
    @Column
    private String exchange;
    @Column
    private String exchangeSuffix;
    @Column
    private String exchangeName;
    @Column
    private String exchangeSegment;
    @Column
    private String exchangeSegmentName;
    @Column
    private String name;
    @Column
    private String date;
    @Column
    private String type;
    @Column
    private String iexId;
    @Column
    private String region;
    @Enumerated(EnumType.STRING)
    @Column
    private Currency currency;
    @Column
    private boolean isEnabled;
    @Column
    private String figi;
    @Column
    private String cik;
    @Column
    private String lei;
}
