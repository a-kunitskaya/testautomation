package com.kunitskaya.webservices;

import com.kunitskaya.webservices.models.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.kunitskaya.test.webservices.Headers.CONTENT_TYPE;
import static org.testng.AssertJUnit.*;

public class WebservicesTest extends WsBaseTest {
    private static final String EMAIL_PATTERN = "[\\w.]+@\\w+\\.\\w{2,4}";

    @Test
    public void validateResponseCode() {
        Response response = wsFacade.get(configProvider.getWsUsersUri());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void validateResponseHeader() {
        Response response = wsFacade.get(configProvider.getWsUsersUri());

        Headers responseHeaders = response.getHeaders();
        String expectedHeaderValue = "application/json; charset=utf-8";

        assertTrue(responseHeaders.hasHeaderWithName(CONTENT_TYPE.getHeader()));
        assertEquals(responseHeaders.get(CONTENT_TYPE.getHeader()).getValue(), expectedHeaderValue);
    }

    @Test
    public void validateResponseBody() {
        Response response = wsFacade.get(configProvider.getWsUsersUri());

        User[] users = response.getBody().as(User[].class);
        assertEquals(users.length, 10);

        for (User user : users) {
            assertNotNull(user.getEmail());
            assertTrue(user.getEmail().matches(EMAIL_PATTERN));
            assertNotNull(user.getName());
            assertNotNull(user.getAddress().getZipcode());
        }
    }
}
