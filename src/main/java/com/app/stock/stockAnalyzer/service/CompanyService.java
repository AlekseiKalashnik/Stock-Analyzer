package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.CompanyResponse;
import com.app.stock.stockAnalyzer.repository.CompanyRepository;
import com.app.stock.stockAnalyzer.repository.CompanyResponseRepository;
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

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@Service
@Slf4j(topic = "CompanyServiceLog:")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {
    private final String TOKEN = "pk_af20fbfdc8844348922c69e10992fdcc";
    private final String ID = "last=1";
    private final String BASE_URL = "https://cloud.iexapis.com/v1";
    private final String GET_COMPANY_BY_SYMBOL = BASE_URL + "/data/core/company?" + ID + "&token=" + TOKEN;
    private final String GET_COMPANY_RESPONSE = BASE_URL + "/ref-data/symbols?" + "token=" + TOKEN;
    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;
    private final CompanyResponseRepository companyResponseRepository;

    @SneakyThrows
    @Transactional
    public void saveCompanies(Queue<CompanyResponse> companyResponseQueue) {
        companyResponseRepository.saveAll(companyResponseQueue);
        log.info("companies have saved");
    }

    //DONE
    @Async
    public CompletableFuture<ConcurrentLinkedQueue<CompanyResponse>> downloadCompaniesData() {
        log.info("begin getCompanyResponse()");
        ResponseEntity<ConcurrentLinkedQueue<CompanyResponse>> response;
        ConcurrentLinkedQueue<CompanyResponse> companiesResponse;
        response = restTemplate
                .exchange(GET_COMPANY_RESPONSE,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        companiesResponse = response.getBody();
        log.info("End of getCompanyResponse()");
        return CompletableFuture.completedFuture(companiesResponse);
    }

    @Transactional
    public List<CompanyResponse> getExistedCompanyList() {
        log.info("begin getExistedCompanyList()");
        return companyResponseRepository.findAll();
    }

//    @Async
//    public CompletableFuture<ConcurrentLinkedQueue<Company>> getCompany() {
//        log.info("begin getCompany()");
//        ResponseEntity<ConcurrentLinkedQueue<Company>> response = restTemplate
//                .exchange(GET_COMPANY_BY_SYMBOL,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Company>>() {
//                        });
//        ConcurrentLinkedQueue<Company> companies = response.getBody();
//        log.info("extract ResponseEntity");
////        System.out.println(companies);
//        log.info("end of getCompany()");
//        if (companies.isEmpty()) {
//            throw new NoCompaniesInCollection();
//        } else {
//            return CompletableFuture.completedFuture(companies);
//        }
//    }

//    @Async
//    @Transactional
//    public void printTopFiveChangePercentCompanies() {
//        log.info("begin printTopFiveChangePercentCompanies()");
//        ResponseEntity<ConcurrentLinkedQueue<Company>> response = restTemplate
//                .exchange(,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<ConcurrentLinkedQueue<Company>>() {
//                        });
//        ConcurrentLinkedQueue<Company> companies = response.getBody();
//        log.info("extract CompanyEntity");
////        System.out.println(companies);
//        log.info("end of printTopFiveChangePercentCompanies()");
//        companyRepository.saveAll(companies);
//        companies.stream().sorted(Comparator.comparing(Company::getPreviousVolume))
//                .sorted(Comparator.comparing(Company::getCompanyName))
//                .limit(5).forEach(System.out::println);
//    }

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
