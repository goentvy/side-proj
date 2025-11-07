package com.entvy.openbidhub.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OnbidApiServiceTest {

    @Test
    void testFetchRawData() {
        // given
        OnbidApiService service = Mockito.mock(OnbidApiService.class);

        // when
        Mockito.when(service.fetchRawData()).thenReturn("mocked response");

        // then
        String result = service.fetchRawData();
        assertEquals("mocked response", result);
    }

    @Test
    void testFetchPage() {
        // given
        OnbidApiService service = Mockito.mock(OnbidApiService.class);

        // when
        Mockito.when(service.fetchPage(1, 10, "20220101", "20221231"))
                .thenReturn("mocked page response");

        // then
        String result = service.fetchPage(1, 10, "20220101", "20221231");
        assertEquals("mocked page response", result);
    }
}