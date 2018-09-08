package com.kunitskaya.webservices.facades;

import com.kunitskaya.webservices.models.ToDo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ToDoWsFacade extends AbstractWsFacade {
    private String uri = configProvider.getWsToDoUri();

    /**
     * Creates a new record by sending POST request
     *
     * @param toDo - object to create record from
     * @return - response
     */
    public Response createToDo(ToDo toDo) {
        Map<String, String> body = new HashMap<>();

        body.put("userId", toDo.getUserId());
        body.put("id", toDo.getId());
        body.put("title", toDo.getTitle());
        body.put("completed", toDo.getCompleted());

        return post(uri, body, ContentType.JSON);
    }

    /**
     * Updates an existing record by sending PUT request
     *
     * @param toDo - object to update record from
     * @return - response
     */
    public Response updateToDo(ToDo toDo) {
        Map<String, String> body = new HashMap<>();
        String id = toDo.getId();

        body.put("userId", toDo.getUserId());
        body.put("id", toDo.getId());
        body.put("title", toDo.getTitle());
        body.put("completed", toDo.getCompleted());

        return put(uri + SEPARATOR + id, body, ContentType.JSON);
    }

    /**
     * Receives all records by sending GET request
     *
     * @return - response
     */
    public Response getToDos() {
        return get(uri);
    }
}
