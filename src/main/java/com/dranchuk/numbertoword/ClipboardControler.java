package com.dranchuk.numbertoword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dranchuk.numbertoword.Converter.buildString;

class ClipboardController {
    static String convertBuffer(String s) {
        double sum, vat;
        int vatPercent;
        Pattern p = Pattern.compile("(\\d+[.,]?\\d+) (\\d+) (\\d+)");
        Matcher m = p.matcher(s.trim());
        if (m.find()) {
            sum = Double.parseDouble(m.group(1)) * Integer.parseInt(m.group(2));
            vatPercent = Integer.parseInt(m.group(3));
            vat = sum * vatPercent / 100;
            sum += vat;
            return buildString(sum, vatPercent);
        }
        return null;
    }
}
