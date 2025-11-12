package com.entvy.openbidhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnbidApiService {

    private final RestTemplate restTemplate;
    private final OnbidXmlParserService parser;

    @Value("${onbid.api.service-key}")
    private String serviceKey;

    // 공공API 호출(전체 데이터)
    public List<String> fetchAllRawXml() {
        URI uri = URI.create("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");
        int numOfRows = 100;
        int pageNo = 1;
        int totalPages = 1;
        List<String> xmlPages = new ArrayList<>();

        while (true) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri)
                    .queryParam("serviceKey", serviceKey)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("pageNo", pageNo);

            String xml = restTemplate.getForObject(builder.toUriString(), String.class);
            if(xml == null || xml.isBlank()) {
                log.warn("API 응답이 비어있습니다. pageNo={}", pageNo);
                break;
            }
            xmlPages.add(xml);

            if (pageNo == 1) {
                int totalCount = parser.extractTotalCount(xml);
                totalPages = (int) Math.ceil((double) totalCount / numOfRows);
                if (totalPages <= 1) break;
            }

            pageNo++;
            if (pageNo > totalPages || pageNo > 50) {
                log.info("페이지 수 초과로 반복 종료: pageNo={}, totalPages={}", pageNo, totalPages);
                break;
            }
        }

        return xmlPages;
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
