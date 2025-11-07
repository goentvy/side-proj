package com.entvy.openbidhub.config;

import com.entvy.openbidhub.service.OnbidApiService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestOnbidApiConfig {
    @Bean RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OnbidApiService onbidApiService(RestTemplate restTemplate) {
        OnbidApiService service = Mockito.spy(new OnbidApiService(restTemplate));
        Mockito.doReturn("mocked response").when(service).fetchRawData();
        return service;
    }
}
