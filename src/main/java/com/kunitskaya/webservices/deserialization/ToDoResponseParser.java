package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ToDoResponseParser implements CustomResponseParser {
    @Override
    public Map<String, String> parseResponse(Response response) {
        String body = response.getBody().asString();

        String id = StringUtils.substringBetween(body, "\"id\": \"", "\"");
        String completed = StringUtils.substringBetween(body, "\"completed\": \"", "\"");
        String title = StringUtils.substringBetween(body, "\"title\": \"", "\"");
        String userId = StringUtils.substringBetween(body, "\"userId\": \"", "\"");

        Map values = new HashMap();
        values.put("userId", userId);
        values.put("id", id);
        values.put("title", title);
        values.put("completed", completed);
        return values;
    }
}
