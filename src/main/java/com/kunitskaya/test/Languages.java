package com.kunitskaya.test;

import java.util.stream.Stream;

public enum Languages {
    ENGLISH("en"),
    RUSSIAN("ru");

    String languageCode;

    Languages(String languageCode) {
        this.languageCode = languageCode;
    }

    public static Languages getLanguage(String languageCode) {
        return Stream.of(values())
                     .filter(lang -> lang.getLanguageCode().equals(languageCode))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No such language is found: " + languageCode));
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
