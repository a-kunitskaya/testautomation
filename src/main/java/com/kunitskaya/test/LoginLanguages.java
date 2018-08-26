package com.kunitskaya.test;

import java.util.stream.Stream;

public enum LoginLanguages {
    ENGLISH("en"),
    RUSSIAN("ru");

    String languageCode;

    LoginLanguages(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public static LoginLanguages getLanguage(String languageCode) {
        return Stream.of(values())
                     .filter(lang -> lang.getLanguageCode().equals(languageCode))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No such language is found: " + languageCode));
    }
}
