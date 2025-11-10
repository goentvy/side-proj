import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class OnbidApi {

    public static void main(String[] args) throws Exception {
        // API URL 설정
        StringBuilder urlBuilder = new StringBuilder(
                "http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList"
        );

        // 파라미터 추가
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" +
                URLEncoder.encode("acf2ba0b6e27eb1ca9299a081a034a30c94d70848411fceb4c65cefa83ae7926", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("DPSL_MTD_CD", "UTF-8") + "=" + URLEncoder.encode("0001", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID_MID", "UTF-8") + "=" + URLEncoder.encode("10200", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_FROM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_TO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_FROM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_TO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("CLTR_NM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("CLTR_MNMT_NO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("PBCT_BEGN_DTM", "UTF-8") + "=" + URLEncoder.encode("20250101", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("PBCT_CLS_DTM", "UTF-8") + "=" + URLEncoder.encode("20251231", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("SIDO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("SGK", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("EMD", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));

        // 연결 설정
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        // 응답 코드 출력
        System.out.println("Response code: " + conn.getResponseCode());

        // 응답 스트림 처리
        BufferedReader rd = (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
                ? new BufferedReader(new InputStreamReader(conn.getInputStream()))
                : new BufferedReader(new InputStreamReader(conn.getErrorStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        // XML 포맷팅 후 출력
        String formattedXml = formatXml(sb.toString());
        System.out.println(formattedXml);

        // 실행 명령 예시
        // java --enable-preview --source 21 OnbidApi.java
    }

    // XML 포맷터 메서드
    public static String formatXml(String xml) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            Source xmlInput = new StreamSource(new StringReader(xml));
            StringWriter stringWriter = new StringWriter();
            Result xmlOutput = new StreamResult(stringWriter);

            transformer.transform(xmlInput, xmlOutput);
            return stringWriter.toString();
        } catch (Exception e) {
            return "XML 포맷팅 실패: " + e.getMessage();
        }
    }
}