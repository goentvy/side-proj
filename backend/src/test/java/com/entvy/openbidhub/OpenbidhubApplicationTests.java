package com.entvy.openbidhub;

import com.entvy.openbidhub.config.TestOnbidApiConfig;
import com.entvy.openbidhub.service.OnbidApiService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestOnbidApiConfig.class)
class OpenbidhubApplicationTests {
    @Autowired
    private OnbidApiService service;

    @Test
    void testRealFetch() {
        String result = service.fetchRawData();
        assertNotNull(result);
        System.out.println(result);
    }

	@Test
	void contextLoads() {
	}
}
