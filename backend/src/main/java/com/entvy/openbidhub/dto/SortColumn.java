package com.entvy.openbidhub.dto;

public enum SortColumn {
    PBCT_BEGN_DTM("pbct_begn_dtm"),
    MIN_BID_PRC("min_bid_prc"),
    APSL_ASES_AVG_AMT("apsl_ases_avg_amt");

    private final String column;

    SortColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public static boolean isValid(String input) {
        for (SortColumn sc : values()) {
            if (sc.name().equalsIgnoreCase(input)) return true;
        }
        return false;
    }

    public static String resolve(String input) {
        for (SortColumn sc : values()) {
            if (sc.name().equalsIgnoreCase(input)) return sc.getColumn();
        }
        return PBCT_BEGN_DTM.getColumn(); // 기본값
    }
}