package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.OnbidItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnbidApiServiceJsonTest {

    @Test
    void testParseJsonToDto() throws Exception {
        // given: 샘플 JSON ( 실제 구조에 맞게 수정 필요 )
        String json = """
        {
            "cltrNm": "종이팩",
            "sido": "강원도",
            "sgk": "인제군",
            "emd": "남면",
            "openPrice": 550000000
        }
        """;

        ObjectMapper mapper = new ObjectMapper();

        // when
        OnbidItemResponse dto = mapper.readValue(json, OnbidItemResponse.class);

        // then
        assertEquals("종이팩", dto.getCltrNm());
        assertEquals("강원도", dto.getSido());
        assertEquals(550000000, dto.getOpenPrice());
    }
}
