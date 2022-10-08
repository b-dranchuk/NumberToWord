package com.dranchuk.numbertoword;

enum Language {
    UKR("Українська", " у т.ч. ПДВ ("), ENG("English", " including VAT (");
    private final String name;

    public String getVat() {
        return vat;
    }

    private final String vat;

    Language(String name, String vat) {
        this.name = name;
        this.vat = vat;
    }

    public String getName() {
        return name;
    }
}
