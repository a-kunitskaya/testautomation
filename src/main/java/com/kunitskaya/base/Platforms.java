package com.kunitskaya.base;

import java.util.stream.Stream;

public enum Platforms {
    MAC,
    WINDOWS;

    public static Platforms getPlatform(String name) {
        return Stream.of(values())
                     .filter(platform -> platform.name().equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No such platform is found: " + name));
    }
}
