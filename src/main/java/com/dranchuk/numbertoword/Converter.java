package com.dranchuk.numbertoword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dranchuk.numbertoword.Setting.*;

public class Converter {

    public static String buildString(double sum, double vatPercent) {
        StringBuilder result = new StringBuilder();

        if (haveVat() && vatPercent != 0) {
            result.append(convert(sum, vatPercent));
        } else {
            result.append(sum);
        }

        if (isFirstCapitalized()) {
            result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
        }
        if (isCapitalizedText()) {
            return result.toString().toUpperCase();
        } else {
            return result.toString();
        }
    }

    public static StringBuilder convert(String number) {
        double sum = Double.parseDouble(number.replaceAll(",", "."));
        return ArrIntToString(parseSumToArrInt(String.format("%.2f", sum)));
    }

    public static StringBuilder convert(double sum, double vatPercent) {
        double vat;
        if (isAddVat()) {
            vat = (sum * vatPercent) / 100;
            sum += vat;
        } else {
            vat = (sum * vatPercent) / (vatPercent + 100);
        }

        StringBuilder result = convert(String.format("%.2f", sum));

        result.append(getLanguage().getVat())
                .append(vatPercent).append("%) ");
        if (isVatByNumber()) {
            result.append(String.format("%.2f ", vat));
        } else {
            result.append(ArrIntToString(parseSumToArrInt(String.format("%.2f ", vat))));

        }
        return result.append(getCurrency());
    }

    private static StringBuilder ArrIntToString(int[] num) {


        return new StringBuilder().append(transform(num[0])).append(" ").append(getCurrency()).append(" ")
                .append(isCentByWord() ? transform(num[1]) : num[1]).append(" ").append(getCurrency1());

    }

    private static int[] parseSumToArrInt(String sum) {
        int[] result = {0, 0};
        //System.out.println(sum);
        Pattern p = Pattern.compile("(\\d+)([.,]\\d\\d?)?");
        Matcher m = p.matcher(sum.trim());
        if (m.find()) {
            result[0] = Integer.parseInt(m.group(1));
            if (sum.matches(".+[.,].+")) {
                result[1] = Integer.parseInt(m.group(2).substring(1) +
                        ((m.group(2).length() < 3) ? "0" : ""));
            }
        }
        return result;
    }

    private static String transform(int num) {
        switch (getLanguage()) {
            case UKR:
                return NumberToWordUkr.transform(num);
            case ENG:
                return NumberToWordEng.transform(num);
            default:
                return "";
        }
    }

}
