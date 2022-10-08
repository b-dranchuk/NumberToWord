package com.dranchuk.numbertoword;

class NumberToWordUkr {
    static String[] One = {"", "один", "два", "три", "чотири", "пять", "шість", "сім", "вісім", "девять", "десять",
            "одинадцять", "дванадцять", "тринадцять", "чотирнадцять", "пятнадцять", "шістнадцять", "сімнадцять",
            "вісімнадцять", "девятнадцять", "", "одна", "дві"};
    static String[] Ten = {"", "", "двадцять", "тридцять", "сорок", "пятдесят", "шістдесят",
            "сімдесят", "вісімдесят", "девяносто"};

    static String[] Hundred = {"", "сто", "двісті", "триста", "чотириста", "пятсот", "шістсот",
            "сімсот", "вісімсот", "девятсот"};
    static String[][] Currency = {{"грив", "копій"},
            {"ня", "ка"},
            {"ні", "ки"},
            {"ень", "ок"}};
    private static boolean female = false;

    public static String transform(int num) {
        if (num == 0) {
            return "нуль";
        }
        return transformer(num);
    }

    private static String milPref(int num) {
        switch (((num / 1000000) % 100 < 10 || (num / 1000000) % 100 > 20) ? (num / 1000000) % 10 : 0) {
            case 2:
            case 3:
            case 4:
                return "и";
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 0:
                return "ів";
            default:
                return "";
        }
    }

    private static String tisPref(int num) {
        switch (((num / 1000) % 100 < 10 || (num / 1000) % 100 > 20) ? (num / 1000) % 10 : 0) {
            case 1:
                return "а";
            case 2:
            case 3:
            case 4:
                return "і";
            default:
                return "";
        }
    }

    private static String transformer(int num) {
        if (num >= 1000000) {
            return (transformer(num / 1000000) + " мільйон" + milPref(num) + " " + transformer(num % 1000000)).trim();
        }
        if (num >= 1000) {
            female = true;
            return (transformer(num / 1000) + " тисяч" + tisPref(num) + " "
                    + transformer(num % 1000)).trim();
        }
        if (num >= 100) {
            return (Hundred[num / 100] + " " + transformer(num % 100)).trim();
        }
        if (num >= 20) {
            return (Ten[num / 10] + " " + transformer(num % 10)).trim();
        }
        return (female && num < 3) ? One[num + 20] : One[num];
    }
}


