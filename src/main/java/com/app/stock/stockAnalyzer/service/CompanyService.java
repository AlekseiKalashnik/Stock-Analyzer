package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final String PUBLIC_TOKEN = "pk_66789463ed6b4a0a9f09669baadd5e3a";
    private final String URL = "https://cloud.iexapis.com/stable";
    private final String GET_ALL_COMPANIES_BY_SYMBOLS = URL + "/ref-data/symbols?token=" + PUBLIC_TOKEN;

    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;

}
