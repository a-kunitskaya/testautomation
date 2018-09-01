package com.kunitskaya.webservices;

import com.kunitskaya.test.webservices.Frameworks;
import com.kunitskaya.webservices.models.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.test.webservices.Headers.CONTENT_TYPE;
import static org.testng.AssertJUnit.*;

public class WebservicesTest extends WsBaseTest {
    private static final String EMAIL_PATTERN = "[\\w.]+@\\w+\\.\\w{2,4}";
    private static final int MAX_NUMBER_OF_USERS = 50;

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseCode(String wsFramework) {
        Frameworks framework = Frameworks.valueOf(wsFramework);
        User[] users = new User[MAX_NUMBER_OF_USERS];

        switch (framework) {
            case REST_ASSURED:
                Response response = wsFacade.get(configProvider.getWsUsersUri());
                assertEquals(response.getStatusCode(), 200);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = wsFacade.get(configProvider.getBaseWsTestUrl(), configProvider.getWsUsersUri(), users);
                assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        }

    }

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseHeader(String wsFramework) {
        Frameworks framework = Frameworks.valueOf(wsFramework);
        User[] users = new User[MAX_NUMBER_OF_USERS];
        String expectedHeaderValue = "application/json; charset=utf-8";

        switch (framework) {
            case REST_ASSURED:
                Response response = wsFacade.get(configProvider.getWsUsersUri());
                Headers responseHeaders = response.getHeaders();
                assertTrue(responseHeaders.hasHeaderWithName(CONTENT_TYPE.getHeader()));
                assertEquals(responseHeaders.get(CONTENT_TYPE.getHeader()).getValue(), expectedHeaderValue);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = wsFacade.get(configProvider.getBaseWsTestUrl(), configProvider.getWsUsersUri(), users);
                HttpHeaders headers = responseEntity.getHeaders();
                assertTrue(headers.containsKey(CONTENT_TYPE.getHeader()));
                assertEquals(headers.getFirst(CONTENT_TYPE.getHeader()), expectedHeaderValue);
                break;
        }
    }

    @Test(dataProvider = "frameworkDataProvider")
    public void validateResponseBody(String wsFramework) {
        User[] users = new User[MAX_NUMBER_OF_USERS];
        Frameworks framework = Frameworks.valueOf(wsFramework);

        switch (framework) {
            case REST_ASSURED:
                Response response = wsFacade.get(configProvider.getWsUsersUri());
                users = response.getBody().as(User[].class);
                break;
            case REST_TEMPLATE:
                ResponseEntity responseEntity = wsFacade.get(configProvider.getBaseWsTestUrl(), configProvider.getWsUsersUri(), users);
                users = (User[]) responseEntity.getBody();
                break;
        }
        assertEquals(users.length, 10);

        for (User user : users) {
            assertNotNull(user.getEmail());
            assertTrue(user.getEmail().matches(EMAIL_PATTERN));
            assertNotNull(user.getName());
            assertNotNull(user.getAddress().getZipcode());
        }
    }

    @DataProvider
    public Object[][] frameworkDataProvider() {
        return new Object[][]{
                {"REST_ASSURED"},
                {"REST_TEMPLATE"}
        };
    }
}
