package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    Integer id;

    String symbol;

    String exchange;

    String exchangeSuffix;

    String exchangeName;

    String exchangeSegment;

    String exchangeSegmentName;

    String name;

    LocalDate date;

    String type;

    String iexId;

    String region;

    String currency;

    boolean isEnabled;

    String figi;

    Integer cik;

    String lei;
}
