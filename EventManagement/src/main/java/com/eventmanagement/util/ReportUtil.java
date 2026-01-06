package com.eventmanagement.util;

import java.util.Map;

public class ReportUtil {

    private ReportUtil() {}

    public static void printReport(Map<String, Long> data) {
        data.forEach((key, value) ->
                System.out.println(key + " : " + value));
    }
}
