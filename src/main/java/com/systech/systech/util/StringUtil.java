package com.systech.systech.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtil {

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static Boolean validString(String text) {

        boolean isEmpty = text == null || StringUtils.isEmpty(text) || text.equalsIgnoreCase("null") || text.isBlank();

        return !isEmpty;

    }

    public static String formatBigDecimalToAmount(BigDecimal amount, String prefix, String suffix) {
        if (amount == null) {
            return "";
        }

        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        StringBuilder result = new StringBuilder();

        if (prefix != null && !prefix.trim().isEmpty()) {
            result.append(prefix.trim()).append(" ");
        }

        result.append(formatter.format(amount));

        if (suffix != null && !suffix.trim().isEmpty()) {
            result.append(" ").append(suffix.trim());
        }

        return result.toString();
    }


}
