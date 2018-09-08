package com.kunitskaya.webservices;

import com.kunitskaya.base.test.TestDataProvider;
import com.kunitskaya.webservices.models.ToDo;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ToDoTest extends WsBaseTest {
    private static final int EXPECTED_RESPONSE_CODE = 200;

    @Test
    public void updateToDo() {
        int userId = 1;
        int id = 1;
        String updatedTitle = "New title";
        boolean isCompleted = false;

        Response response = toDoWsFacade.updateToDo(null, null);
        ToDo toDo = response.getBody().as(ToDo.class);

        softAssert.assertEquals(response.getStatusCode(), EXPECTED_RESPONSE_CODE);
        softAssert.assertEquals(toDo.getTitle(), updatedTitle);
    }

    @Test
    public void createToDo() {
        ToDo expectedToDo = TestDataProvider.getDefaultTodo();

        Response response = toDoWsFacade.createToDo(expectedToDo);
        ToDo actualTodo = response.getBody().as(ToDo.class);

        assertEquals(response.getStatusCode(), 201);
        assertEquals(actualTodo, expectedToDo);


    }

}
