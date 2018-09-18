package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;

import java.util.Map;

public interface CustomResponseParser {
    Object parseResponse(Response response);
}
