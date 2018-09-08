package com.kunitskaya.webservices;

import com.kunitskaya.webservices.models.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.webservices.Frameworks.REST_ASSURED;
import static com.kunitskaya.webservices.Frameworks.REST_TEMPLATE;
import static com.kunitskaya.webservices.Headers.CONTENT_TYPE;

public class UsersWsTest extends WsBaseTest {
    private static final String EMAIL_PATTERN = "[\\w.]+@\\w+\\.\\w{2,4}";

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseCode(Frameworks framework) {
        switch (framework) {
            case REST_ASSURED:
                Response response = usersWsFacade.getUsersRA();
                int expectedCode = 200;
                softAssert.assertEquals(response.getStatusCode(), expectedCode);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = usersWsFacade.getUsersRT();
                softAssert.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        }

    }

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseHeader(Frameworks framework) {
        String expectedHeaderValue = "application/json; charset=utf-8";

        switch (framework) {
            case REST_ASSURED:
                Response response = usersWsFacade.getUsersRA();
                Headers responseHeaders = response.getHeaders();
                softAssert.assertTrue(responseHeaders.hasHeaderWithName(CONTENT_TYPE.getHeader()));
                softAssert.assertEquals(responseHeaders.get(CONTENT_TYPE.getHeader()).getValue(), expectedHeaderValue);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = usersWsFacade.getUsersRT();
                HttpHeaders headers = responseEntity.getHeaders();
                softAssert.assertTrue(headers.containsKey(CONTENT_TYPE.getHeader()));
                softAssert.assertEquals(headers.getFirst(CONTENT_TYPE.getHeader()), expectedHeaderValue);
                break;
        }
    }

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseBody(Frameworks framework) {
        User[] users = null;
        switch (framework) {
            case REST_ASSURED:
                Response response = usersWsFacade.getUsersRA();
                users = response.getBody().as(User[].class);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = usersWsFacade.getUsersRT();
                users = (User[]) responseEntity.getBody();
                break;
        }

        int expectedNumberOfUsers = 10;
        softAssert.assertEquals(users.length, expectedNumberOfUsers);

        for (User user : users) {
            softAssert.assertNotNull(user.getEmail());
            softAssert.assertTrue(user.getEmail().matches(EMAIL_PATTERN));
            softAssert.assertNotNull(user.getName());
            softAssert.assertNotNull(user.getAddress().getZipcode());
        }
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(1);

        Response response = usersWsFacade.deleteUser(user);
        int expectedCode = 200;
        softAssert.assertEquals(response.getStatusCode(), expectedCode);
    }

    @DataProvider
    public Object[][] frameworkDataProvider() {
        return new Object[][]{
                {REST_ASSURED},
                {REST_TEMPLATE}
        };
    }
}
