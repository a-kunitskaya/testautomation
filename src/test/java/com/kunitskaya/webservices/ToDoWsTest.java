package com.kunitskaya.webservices;

import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.webservices.deserialization.CustomResponseParser;
import com.kunitskaya.webservices.models.ToDo;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static com.kunitskaya.base.utils.NumbersUtil.getRandomInt;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.testng.AssertJUnit.assertEquals;

public class ToDoWsTest extends WsBaseTest {

    @Test
    public void updateToDo() {
        ToDo expectedToDo = TestDataProvider.getDefaultTodo();
        int numberOfRecords = toDoWsFacade.getToDos().as(ToDo[].class).length;

        //setting id of existing record
        expectedToDo.setId(valueOf(getRandomInt(1, numberOfRecords)));
        expectedToDo.setUserId(valueOf(getRandomInt(0, 10000)));
        expectedToDo.setTitle(randomAlphanumeric(1, 50));

        Response response = toDoWsFacade.updateToDo(expectedToDo);
        ToDo actualToDo = response.as(ToDo.class);
        System.out.println(actualToDo);

        assertEquals(response.getStatusCode(), HttpStatus.OK.value());
        assertEquals(actualToDo, expectedToDo);
    }

    @Test
    public void createToDo() throws InstantiationException, IllegalAccessException {
        ToDo expectedToDo = TestDataProvider.getDefaultTodo();

        int numberOfRecords = toDoWsFacade.getToDos().as(ToDo[].class).length;

        //setting id of a nonexistent record
        expectedToDo.setId(valueOf(getRandomInt(numberOfRecords, numberOfRecords + 5)));

        Response response = toDoWsFacade.createToDo(expectedToDo);

        //using custom parser & mapper since response does not always has header known to RestAssured
        ToDo actualTodo = new CustomResponseParser().parseResponse(response, ToDo.class);

        System.out.println(actualTodo.toString());

        assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());
        assertEquals(actualTodo, expectedToDo);
    }
}
