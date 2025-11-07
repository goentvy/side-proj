package com.entvy.openbidhub.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OnbidItemResponseListTest {

    @Test
    void testJsonArrayParsing() throws Exception {
        String jsonArray = """
        [
            {
                "cltrMnmtNo": "2025-10893-001",
                "cltrNm": "충청남도 아산시 신창면 가내리 413-2 ",
                "sido": null,
                "sgk": null,
                "emd": null,
                "goodsPrice": null,
                "openPrice": null,
                "pbctBegnDt": null,
                "pbctClsDt": null,
                "bidStatus": null
            },
            {
                "cltrMnmtNo": "2025-10893-002",
                "cltrNm": "서울특별시 강남구 역삼동 123-45",
                "sido": "서울특별시",
                "sgk": "강남구",
                "emd": "역삼동",
                "goodsPrice": 800000000,
                "openPrice": 750000000,
                "pbctBegnDt": "20251101",
                "pbctClsDt": "20251107",
                "bidStatus": "입찰중"
            }
        ]
        """;

        ObjectMapper mapper = new ObjectMapper();
        List<OnbidItemResponse> items = mapper.readValue(jsonArray, new TypeReference<>() {});

        assertEquals(2, items.size());
        assertEquals("2025-10893-001", items.get(0).getCltrMnmtNo());
        assertEquals("강남구", items.get(1).getSgk());
        assertEquals("입찰중", items.get(1).getBidStatus());
    }
}