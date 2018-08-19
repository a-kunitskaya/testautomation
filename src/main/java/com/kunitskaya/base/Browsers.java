package com.kunitskaya.base;

import java.util.stream.Stream;

public enum Browsers {
    CHROME("chrome");

    private String name;

    Browsers(String name) {
        this.name = name;
    }

    public static Browsers getBrowser(String name) {
        return Stream.of(values())
                     .filter(browser -> browser.getName().equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No such browser is found: " + name));
    }

    public String getName() {
        return name;
    }
}
