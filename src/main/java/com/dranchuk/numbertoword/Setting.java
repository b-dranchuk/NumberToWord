package com.dranchuk.numbertoword;

class Setting {
    private static Language language;
    private static boolean Vat;
    private static boolean addVat;
    private static boolean VatByNumber;
    private static boolean CentByWord;
    private static String currency;
    private static String currency1;
    private static boolean firstCapitalized;
    private static boolean capitalizedText;

    public static boolean isFirstCapitalized() {
        return firstCapitalized;
    }

    public static void setFirstCapitalized(boolean firstCapitalized) {
        Setting.firstCapitalized = firstCapitalized;
    }

    public static boolean isCapitalizedText() {
        return capitalizedText;
    }

    public static void setCapitalizedText(boolean capitalizedText) {
        Setting.capitalizedText = capitalizedText;
    }

    public static Language getLanguage() {
        return language;
    }

    public static void setLanguage(Language language) {
        Setting.language = language;
    }

    public static boolean haveVat() {
        return Vat;
    }

    public static void setVat(boolean vat) {
        Vat = vat;
    }

    public static boolean isAddVat() {
        return addVat;
    }

    public static void setAddVat(boolean AddVat) {
        addVat = AddVat;
    }

    public static boolean isVatByNumber() {
        return VatByNumber;
    }

    public static void setVatByNumber(boolean VatByNumber) {
        Setting.VatByNumber = VatByNumber;
    }

    public static boolean isCentByWord() {
        return CentByWord;
    }

    public static void setCentByWord(boolean centByWord) {
        Setting.CentByWord = centByWord;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        Setting.currency = currency;
    }

    public static String getCurrency1() {
        return currency1;
    }

    public static void setCurrency1(String currency1) {
        Setting.currency1 = currency1;
    }


}
