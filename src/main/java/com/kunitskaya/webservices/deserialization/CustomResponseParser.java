package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;

import java.util.Map;

public interface CustomResponseParser {
    Map<String, String> parseResponse(Response response);
}
