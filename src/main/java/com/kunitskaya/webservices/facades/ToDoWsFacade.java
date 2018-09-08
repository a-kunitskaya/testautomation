package com.kunitskaya.webservices.facades;

import com.kunitskaya.webservices.models.ToDo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ToDoWsFacade extends AbstractWsFacade {
    private String uri = configProvider.getWsToDoUri();

    public Response createToDo(ToDo toDo) {
        Map<String, String> body = new HashMap<>();

        body.put("userId", toDo.getUserId());
        body.put("id", toDo.getId());
        body.put("title", toDo.getTitle());
        body.put("completed", toDo.getCompleted());

        return post(uri, body, ContentType.JSON);

    }

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
     * Makes PUT request
     *
     * @param userId      - userId to pass in request body
     * @param id          - id to pass in request body
     * @param title       - title to pass in request body
     * @param isCompleted - isCompleted to pass in request body
     * @return - response json object
     */
    public Response putt(int userId, int id, String title, boolean isCompleted) {
        Map<String, String> body = new HashMap<>();
        body.put("userId", String.valueOf(userId));
        body.put("id", String.valueOf(id));
        body.put("title", title);
        body.put("completed", String.valueOf(isCompleted));
        return RestAssured.given()
                          .when()
                          .contentType("application/json; charset=utf-8")
                          .body(body)
                          .put(uri + "/" + id)
                          .andReturn();
    }

    /**
     * Makes POST request
     *
     * @param userId      - userId to pass in request body
     * @param id          - id to pass in request body
     * @param title       - title to pass in request body
     * @param isCompleted - isCompleted to pass in request body
     * @return - response json object
     */
    public Response postt(int userId, int id, String title, boolean isCompleted) {
        Map<String, String> body = new HashMap<>();
        body.put("userId", String.valueOf(userId));
        body.put("id", String.valueOf(id));
        body.put("title", title);
        body.put("completed", String.valueOf(isCompleted));

        return RestAssured.given()
                          .when()
                          .contentType("application/json; charset=utf-8")
                          .body(body)
                          .post(uri + "/" + id)
                          .andReturn();
    }
}
