package com.app.stock.stockAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    //TODO nested class
    String address;
    String address2;
    String ceo;
    String city;
    String companyName;
    String country;
    String date;
    Double employees;
    String exchange;
    String exchangeCode;
    String industry;
    String issuetype;
    String longDescription;
    Double marketcap;
    String phone;
    String primarySicCode;
    String sector;
    String securityName;
    String securityType;
    String shortDescription;
    String state;
    String symbol;
    String website;
    String zip;
    String id;
    String key;
    String subkey;
    String updated;
}
