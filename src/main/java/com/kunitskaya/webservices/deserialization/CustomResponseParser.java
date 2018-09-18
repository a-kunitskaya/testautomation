package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;

public interface CustomResponseParser {
    Object parseResponse(Response response);
}
