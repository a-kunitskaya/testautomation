package com.kunitskaya.base;

public enum Browsers {
    CHROME("chrome");

    private String type;

    Browsers(String type) {
        this.type = type;
    }

    public static Browsers getBrowser(String type) {
        if (type.equals(CHROME.getType())) {
            return CHROME;
        } else {
            return null;
        }
    }

    public String getType() {
        return type;
    }
}
