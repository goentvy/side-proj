package com.entvy.openbidhub.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DateRangeUtil {
    public static Map<String, String> getSixMonthRange() {
        LocalDate today = LocalDate.now();
        LocalDate sixMonthsAgo = today.minusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Map<String, String> range = new HashMap<>();
        range.put("from", sixMonthsAgo.format(formatter));
        range.put("to", today.format(formatter));
        return range;
    }
}
