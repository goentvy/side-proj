package com.entvy.openbidhub.service;

import com.entvy.openbidhub.openapi.xml.OnbidItemRawDto;
import com.entvy.openbidhub.openapi.xml.OnbidResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.List;

@Slf4j
@Service
public class OnbidXmlParserService {
    // JAXB XML 파싱
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
            log.warn("XML 파싱 실패: {}", e.getMessage());
            log.debug("원본 XML 일부: {}", xml.substring(0, Math.min(xml.length(), 500)));
            throw new RuntimeException("XML 파싱 실패", e);
        }
    }

    // totalCount 추출
    public int extractTotalCount(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // XML 파싱 시 XXE 공격 방지를 위한 보안 설정
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));

            NodeList countNodes = doc.getElementsByTagName("totalCount");
            if (countNodes.getLength() > 0) {
                String countText = countNodes.item(0).getTextContent();
                return Integer.parseInt(countText.trim());
            }
        } catch (Exception e) {
            log.warn("totalCount 파싱 실패: {} (XML 일부: {})", e.getMessage(), xml.substring(0, Math.min(xml.length(), 300)));
        }
        return 0;
    }
}
