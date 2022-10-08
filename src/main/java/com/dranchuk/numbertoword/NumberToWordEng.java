package com.dranchuk.numbertoword;

class NumberToWordEng {
    static String[] One = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] Ten = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"};

    public static String transform(int num) {
        if (num == 0) {
            return "Zero";
        }
        return transformer(num);
    }

    private static String transformer(int num) {
        if (num >= 1000000) {
            return (transformer(num / 1000000) + " Million " + transformer(num % 1000000)).trim();
        }
        if (num >= 1000) {
            return (transformer(num / 1000) + " Thousand " + transformer(num % 1000)).trim();
        }
        if (num >= 100) {
            return (transformer(num / 100) + " Hundred " + transformer(num % 100)).trim();
        }
        if (num >= 20) {
            return (Ten[num / 10] + " " + transformer(num % 10)).trim();
        }
        return One[num];
    }
}


