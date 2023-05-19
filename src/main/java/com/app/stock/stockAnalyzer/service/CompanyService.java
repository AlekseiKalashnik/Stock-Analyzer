package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.Company;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Slf4j(topic = "CompanyServiceLog:")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {
    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    private final String ID = "last=1";
    private final String BASE_URL = "https://cloud.iexapis.com/v1";
    private final String GET_COMPANY_BY_SYMBOL = BASE_URL + "/data/core/company?" + ID + "&token=" + TOKEN;
    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;

    @Async
    public CompletableFuture<Queue<Company>> getCompanyQueue() {
        log.info("begin getCompanyQueue()");
        ResponseEntity<ConcurrentLinkedQueue<Company>> response = restTemplate
                .exchange(GET_COMPANY_BY_SYMBOL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Company>>() {
                        });
        ConcurrentLinkedQueue<Company> companies = response.getBody();
        log.info("extract ResponseEntity");
//        System.out.println(companies);
        log.info("end of getCompanyQueue()");
        return CompletableFuture.completedFuture(companies);
    }

    @SneakyThrows
    @Transactional
    public void saveCompany(){
        companyRepository.saveAll(getCompanyQueue().get());
        log.info("company has saved");
    }

//    public List<Company> getCompany() {
//        System.out.println("Start");
//        ResponseEntity<List<Company>> response = restTemplate
//                .exchange(GET_COMPANY_BY_SYMBOL,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<List<Company>>() {
//                        });
//        List<Company> companies = response.getBody().stream()
//                .collect(Collectors.toList());
//        System.out.println("TEST");
//        System.out.println(companies);
//        System.out.println("End");
//        return companies;
//    }
}
