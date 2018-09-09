package com.kunitskaya.webservices.deserialization;

import com.kunitskaya.webservices.models.ToDo;

import java.util.Map;

public class ToDoResponseMapper implements CustomResponseMapper {
    @Override
    public ToDo map(Map<String, String> body) {
        return new ToDo(body.get("userId"), body.get("id"), body.get("title"), body.get("completed"));
    }
}
