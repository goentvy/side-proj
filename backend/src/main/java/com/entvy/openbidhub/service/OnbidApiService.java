package com.entvy.openbidhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OnbidApiService {

    private final RestTemplate restTemplate;

    @Value("${onbid.api.service-key}")
    private String serviceKey;

//    @PostConstruct
//    public void init() {
//        try {
//            System.out.println("Injected serviceKey = " + serviceKey);
//            System.out.println("System.getenv = " + System.getenv("ONBID_SERVICE_KEY"));
//            System.out.println("restTemplate = " + restTemplate);
//        } catch (Exception e) {
//            System.out.println("❌ PostConstruct failed: " + e.getMessage());
//        }
//    }

    // 공공API 호출(전체 데이터)
    public String fetchRawXml() {
        URI uri = URI.create("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri)
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", "100")
                .queryParam("pageNo", "1");

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }

    public String fetchRawData() {
        URI uri = URI.create("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .uri(uri)
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", "10")
                .queryParam("pageNo", "1")
                .queryParam("DPSL_MTD_CD", "0001")
                .queryParam("CTGR_HIRK_ID", "10000")
                .queryParam("CTGR_HIRK_ID_MID", "10100")
                .queryParam("SIDO", "서울특별시")
                .queryParam("SGK", "")
                .queryParam("EMD", "")
                .queryParam("GOODS_PRICE_FROM", "")
                .queryParam("GOODS_PRICE_TO", "")
                .queryParam("OPEN_PRICE_FROM", "")
                .queryParam("OPEN_PRICE_TO", "")
                .queryParam("CLTR_NM", "")
                .queryParam("PBCT_BEGN_DTM", "")
                .queryParam("PBCT_CLS_DTM", "")
                .queryParam("CLTR_MNMT_NO", "");

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        return response.getBody();
    }

    public String fetchPage(int pageNo, int numOfRows, String fromDate, String toDate) {
        URI uri = URI.create("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .uri(uri)
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", numOfRows)
                .queryParam("pageNo", pageNo)
                .queryParam("PBCT_BEGN_DTM", fromDate)
                .queryParam("PBCT_CLS_DTM", toDate);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        return response.getBody();
    }
}
