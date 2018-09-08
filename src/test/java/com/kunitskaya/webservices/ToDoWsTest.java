package com.kunitskaya.webservices;

import com.kunitskaya.base.test.TestDataProvider;
import com.kunitskaya.webservices.deserialization.JsonDataDeserializer;
import com.kunitskaya.webservices.models.ToDo;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ToDoWsTest extends WsBaseTest {

    @Test
    public void updateToDo() {
        ToDo expectedToDo = TestDataProvider.getDefaultTodo();

        //setting id of existing object
        expectedToDo.setId("1");

        Response response = toDoWsFacade.updateToDo(expectedToDo);
        ToDo actualToDo = (ToDo) JsonDataDeserializer.deserializeFromJson(response, ToDo.class);

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.OK.value());
        softAssert.assertEquals(actualToDo, expectedToDo);
    }

    @Test
    public void createToDo() {
        ToDo expectedToDo = TestDataProvider.getDefaultTodo();

        Response response = toDoWsFacade.createToDo(expectedToDo);
        ToDo actualTodo = (ToDo) JsonDataDeserializer.deserializeFromJson(response, ToDo.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());
        assertEquals(actualTodo, expectedToDo);
    }
}
