import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

void main() throws Exception {
    StringBuilder urlBuilder = new StringBuilder("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList");
    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode("acf2ba0b6e27eb1ca9299a081a034a30c94d70848411fceb4c65cefa83ae7926", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("DPSL_MTD_CD", "UTF-8") + "=" + URLEncoder.encode("0001", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID_MID", "UTF-8") + "=" + URLEncoder.encode("10100", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_FROM", "UTF-8") + "=" + URLEncoder.encode("522740000", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("GOODS_PRICE_TO", "UTF-8") + "=" + URLEncoder.encode("617393000", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_FROM", "UTF-8") + "=" + URLEncoder.encode("522740000", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("OPEN_PRICE_TO", "UTF-8") + "=" + URLEncoder.encode("617393000", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("CLTR_NM", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("CLTR_MNMT_NO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("PBCT_BEGN_DTM", "UTF-8") + "=" + URLEncoder.encode("20220101", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("PBCT_CLS_DTM", "UTF-8") + "=" + URLEncoder.encode("20221231", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("SIDO", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("SGK", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
    urlBuilder.append("&" + URLEncoder.encode("EMD", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));

    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");

    System.out.println("Response code: " + conn.getResponseCode());

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

    System.out.println(sb.toString());
}