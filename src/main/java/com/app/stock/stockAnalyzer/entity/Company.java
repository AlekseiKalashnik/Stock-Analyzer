package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Column
    private String address;
    @Column
    private String address2;
    @Column
    private String ceo;
    @Column
    private String city;
    @Column
    private String companyName;
    @Column
    private String country;
    @Column
    private String date;
    @Column
    private Double employees;
    @Column
    private String exchange;
    @Column
    private String exchangeCode;
    @Column
    private String industry;
    @Column
    private String issuetype;
    @Column(length = 5000)
    private String longDescription;
    @Column
    private Double marketcap;
    @Column
    private String phone;
    @Column
    private String primarySicCode;
    @Column
    private String sector;
    @Column
    private String securityName;
    @Column
    private String securityType;
    @Column(length = 5000)
    private String shortDescription;
    @Column
    private String state;
    @Column
    @Id
    private String symbol;
    @Column
    private String website;
    @Column
    private String zip;
    @Column
    private String id;
    @Column
    private String key;
    @Column
    private String subkey;
    @Column
    private String updated;
}
