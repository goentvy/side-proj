package com.entvy.openbidhub.service;

import jakarta.annotation.PostConstruct;
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
                .queryParam("SIDO", "강원도")
                .queryParam("SGK", "인제군")
                .queryParam("EMD", "남면")
                .queryParam("GOODS_PRICE_FROM", "522740000")
                .queryParam("GOODS_PRICE_TO", "617393000")
                .queryParam("OPEN_PRICE_FROM", "522740000")
                .queryParam("OPEN_PRICE_TO", "617393000")
                .queryParam("CLTR_NM", "종이팩")
                .queryParam("PBCT_BEGN_DTM", "20171218")
                .queryParam("PBCT_CLS_DTM", "20171218")
                .queryParam("CLTR_MNMT_NO", "2012-1141-001291");

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        return response.getBody();
    }

    public String fetchPage(int pageNo, int numOfRows, String fromDate, String toDate) {
        URI uri = URI.create("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");
        String serviceKey = "acf2ba0b6e27eb1ca9299a081a034a30c94d70848411fceb4c65cefa83ae7926";

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
