package com.kunitskaya.base;

public enum Platforms {
    MAC("MAC"),
    WINDOWS("WINDOWS");


    private String name;

    Platforms(String name) {
        this.name = name;
    }

    public static Platforms getPlatform(String name) {
        if (name.equals(MAC.getName())) {
            return MAC;
        } else if (name.equals(WINDOWS.getName())) {
            return WINDOWS;
        } else {
            throw new NullPointerException("No such platform is found");
        }
    }

    public String getName() {
        return name;
    }
}
