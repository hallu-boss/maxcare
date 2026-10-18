package com.maxcare.domain;

import java.util.Locale;

public enum Language {
    PL("pl"),
    EN("en");

    private final Locale locale;

    Language(String tag) {
        this.locale = Locale.forLanguageTag(tag);
    }


    public Locale toLocale() {
        return locale;
    }

    public static Language fromLocale(Locale locale) {
        return locale != null && "en".equalsIgnoreCase(locale.getLanguage()) ? EN : PL;
    }
}
