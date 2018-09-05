package com.kunitskaya.webservices;

import com.kunitskaya.test.webservices.models.Todo;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ToDoTest extends WsBaseTest {
    private static final int EXPECTED_RESPONSE_CODE = 200;

    @Test
    public void updateToDo() {
        int userId = 1;
        int id = 1;
        String updatedTitle = "New title";
        boolean isCompleted = false;

        Response response = toDoWsFacade.put(userId, id, updatedTitle, isCompleted);
        Todo todo = response.getBody().as(Todo.class);

        softAssert.assertEquals(response.getStatusCode(), EXPECTED_RESPONSE_CODE);
        softAssert.assertEquals(todo.getTitle(), updatedTitle);
    }

    @Test
    public void createToDo() {
        int userId = 20;
        int id = 20;
        String title = "A new created todo";
        boolean isCompleted = false;

        Response response = toDoWsFacade.put(userId, id, title, isCompleted);
        Todo todo = response.getBody().as(Todo.class);

        softAssert.assertEquals(response.getStatusCode(), EXPECTED_RESPONSE_CODE);
        softAssert.assertEquals(todo.getUserId(), String.valueOf(userId));
        softAssert.assertEquals(todo.getId(), String.valueOf(id));
        softAssert.assertEquals(todo.getTitle(), title);
        softAssert.assertEquals(todo.getCompleted(), String.valueOf(isCompleted));

    }

}
