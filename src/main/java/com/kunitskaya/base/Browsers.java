package com.kunitskaya.base;

public enum Browsers {
    CHROME("chrome");

    private String name;

    Browsers(String name) {
        this.name = name;
    }

    public static Browsers getBrowser(String name) {
        if (name.equals(CHROME.getName())) {
            return CHROME;
        } else {
            throw new NullPointerException("No such browser is found");
        }
    }

    public String getName() {
        return name;
    }
}
