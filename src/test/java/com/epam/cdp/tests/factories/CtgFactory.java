package com.epam.cdp.tests.factories;

import java.util.stream.Stream;

public class CtgFactory {

    static Stream<Double> ctgProvider(){
        return Stream.of(2.5, 15.0, -1.0);
    }
}
