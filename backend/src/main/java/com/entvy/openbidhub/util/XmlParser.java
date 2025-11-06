package com.entvy.openbidhub.util;

import com.entvy.openbidhub.dto.OnbidItemDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static List<OnbidItemDto> parse(String xml) throws Exception {
        List<OnbidItemDto> result = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));

        NodeList items = doc.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element el = (Element) items.item(i);
            OnbidItemDto dto = new OnbidItemDto();
            dto.setCltrNm(getText(el, "CLTR_NM"));
            dto.setCltrMnmtNo(getText(el, "CLTR_MNMT_NO"));
            dto.setLdnmAdrs(getText(el, "LDNM_ADRS"));
            dto.setNmrdAdrs(getText(el, "NMRD_ADRS"));
            dto.setLdnmPnu(getText(el, "LDNM_PNU"));
            dto.setCtgrFullNm(getText(el, "CTGR_FULL_NM"));
            dto.setDpslMtdNm(getText(el, "DPSL_MTD_NM"));
            dto.setBidMtdNm(getText(el, "BID_MTD_NM"));
            dto.setMinBidPrc(getText(el, "MIN_BID_PRC"));
            dto.setApslAsesAvgAmt(getText(el, "APSL_ASES_AVG_AMT"));
            dto.setFeeRate(getText(el, "FEE_RATE"));
            dto.setPbctBegnDtm(getText(el, "PBCT_BEGN_DTM"));
            dto.setPbctClsDtm(getText(el, "PBCT_CLS_DTM"));
            dto.setPbctCltrStatNm(getText(el, "PBCT_CLTR_STAT_NM"));
            dto.setGoodsNm(getText(el, "GOODS_NM"));
            dto.setIqryCnt(getText(el, "IQRY_CNT"));
            dto.setCltrImgFiles(getText(el, "CLTR_IMG_FILES"));
            dto.setPlnmNo(getText(el, "PLNM_NO"));
            dto.setPbctNo(getText(el, "PBCT_NO"));
            dto.setPbctCdtnNo(getText(el, "PBCT_CDTN_NO"));
            dto.setCltrNo(getText(el, "CLTR_NO"));
            dto.setCltrHstrNo(getText(el, "CLTR_HSTR_NO"));
            dto.setScrnGrpCd(getText(el, "SCRN_GRP_CD"));
            dto.setBidMnmtNo(getText(el, "BID_MNMT_NO"));
            dto.setDpslMtdCd(getText(el, "DPSL_MTD_CD"));
            dto.setUscbCnt(getText(el, "USCBD_CNT"));
            dto.setManf(getText(el, "MANF"));
            dto.setMdl(getText(el, "MDL"));
            dto.setNrgt(getText(el, "NRGT"));
            dto.setGrbx(getText(el, "GRBX"));
            dto.setEndpc(getText(el, "ENDPC"));
            dto.setVhclMlge(getText(el, "VHCL_MLGE"));
            dto.setFuel(getText(el, "FUEL"));
            result.add(dto);
        }
        return result;
    }

    private static String getText(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        return (list.getLength() > 0) ? list.item(0).getTextContent() : "";
    }

    public static int extractTotalCount(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            return Integer.parseInt(doc.getElementsByTagName("totalCount").item(0).getTextContent());
        } catch (Exception e) {
            throw new RuntimeException("totalCount 파싱 실패", e);
        }
    }
}