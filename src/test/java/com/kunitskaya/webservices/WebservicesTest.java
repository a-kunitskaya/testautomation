package com.kunitskaya.webservices;

import com.kunitskaya.business.models.webservices.User;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class WebservicesTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void validateResponseCode() {
        Response response = RestAssured.when()
                                       .get("/users")
                                       .andReturn();

        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void validateResponseHeader() {
        Response response = RestAssured.when()
                                       .get("/users")
                                       .andReturn();

        Headers responseHeaders = response.getHeaders();
        String expectedContentTypeHeader = "Content-Type";
        String expectedContentTypeHeaderValue = "application/json; charset=utf-8";

        assertTrue(responseHeaders.hasHeaderWithName(expectedContentTypeHeader));
        assertEquals(responseHeaders.get(expectedContentTypeHeader).getValue(), expectedContentTypeHeaderValue);
    }

    @Test
    public void validateResponseBody() {
        Response response = RestAssured.when()
                                       .get("/users")
                                       .andReturn();

        User[] users = response.getBody().as(User[].class);

        for (User user : users) {
            System.out.println(user.toString());
        }
        assertEquals(users.length, 10);
    }
}