package com.kunitskaya.webservices.deserialization;

import java.util.Map;

public interface CustomResponseMapper {
    Object map (Map<String, String> body);
}
