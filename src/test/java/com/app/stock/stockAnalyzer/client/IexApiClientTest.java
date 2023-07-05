//package com.app.stock.stockAnalyzer.client;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//class IexApiClientTest {
//
//
//    @Test
//    void ensureThatCompaniesIexApiCallReturnStatusCode200() throws IOException, InterruptedException {
//        HttpResponse<String> response = getCompaniesRequest();
//        assertThat(response.statusCode()).isEqualTo(200);
//    }
//
//    @Test
//    void ensureThatCompaniesJsonIsReturnedAsContentType() throws IOException, InterruptedException {
//
//        HttpResponse<String> response = getCompaniesRequest();
//        Optional<String> firstValue = response.headers().firstValue("Content-Type");
//
//        assertThat(firstValue.get()).startsWith("application/json");
//    }
//
//    @Test
//    void ensureThatStockIexApiCallReturnStatusCode200() throws IOException, InterruptedException {
//        HttpResponse<String> response = getStockRequest("aapl");
//
//        assertThat(response.statusCode()).isEqualTo(200);
//    }
//
//    @Test
//    void ensureThatStockJsonIsReturnedAsContentType() throws Exception {
//        HttpResponse<String> response = getStockRequest("fb");
//        Optional<String> firstValue = response.headers().firstValue("Content-Type");
//
//        assertThat(firstValue.get()).startsWith("application/json");
//    }
//
//    private HttpResponse<String> getCompaniesRequest() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newBuilder().build();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.iex.cloud/v1/data/CORE/REF_DATA" +
//                "?token=sk_248dd1dd76ee4318bea2815dbd0cd6b6")).build();
//        return client.send(request, HttpResponse.BodyHandlers.ofString());
//    }
//
//    private HttpResponse<String> getStockRequest(String symbol) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newBuilder().build();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.iex.cloud/v1/data/core/quote/" + symbol +
//                "?token=sk_248dd1dd76ee4318bea2815dbd0cd6b6")).build();
//        return client.send(request, HttpResponse.BodyHandlers.ofString());
//    }
//}
