package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private LocalDate date;
    @Column
    private String type;
    @Column
    private String iexId;
    @Column
    private String region;
    @Column
    @Enumerated(value = EnumType.STRING)
    Currency currency;
    @Column
    private boolean isEnabled;
    @Column
    private String figi;
    @Column
    private Integer cik;
    @Column
    private String lei;
}
