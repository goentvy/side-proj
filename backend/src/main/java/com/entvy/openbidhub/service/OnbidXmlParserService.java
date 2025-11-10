package com.entvy.openbidhub.service;

import com.entvy.openbidhub.openapi.xml.OnbidItemRawDto;
import com.entvy.openbidhub.openapi.xml.OnbidResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;

@Slf4j
@Service
public class OnbidXmlParserService {
    public List<OnbidItemRawDto> parse(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(OnbidResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            OnbidResponse response = (OnbidResponse) unmarshaller.unmarshal(reader);

            if (response == null || response.getBody() == null ||
                    response.getBody().getItems() == null ||
                    response.getBody().getItems().getItemList() == null) {
                return List.of(); // 빈 리스트 반환
            }

            return response.getBody().getItems().getItemList();
        } catch (JAXBException e) {
            log.warn("XML 파싱 결과가 비어있습니다. 원본 XML: {}", xml);
            throw new RuntimeException("XML 파싱 실패", e);
        }

    }
}
