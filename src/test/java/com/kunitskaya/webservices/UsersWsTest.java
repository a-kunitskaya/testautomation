package com.kunitskaya.webservices;

import com.kunitskaya.webservices.models.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.base.utils.NumbersUtil.getRandomInt;
import static com.kunitskaya.webservices.Frameworks.REST_ASSURED;
import static com.kunitskaya.webservices.Frameworks.REST_TEMPLATE;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UsersWsTest extends WsBaseTest
{
	private static final String EMAIL_PATTERN = "[\\w.]+@\\w+\\.\\w{2,4}";

	@Test(dataProvider = "frameworkDataProvider")
	public void validateResponseCode(Frameworks framework) {
		switch (framework) {
		case REST_ASSURED:
			Response response = usersWsFacade.getUsersRA();
			assertEquals(response.getStatusCode(), HttpStatus.OK.value());
			break;
		case REST_TEMPLATE:
			ResponseEntity responseEntity = usersWsFacade.getUsersRT();
			assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
			break;
		default:
			throw new IllegalArgumentException("No such framework is found " + framework.name());
		}

	}

	@Test(dataProvider = "frameworkDataProvider")
	public void validateResponseHeader(Frameworks framework) {
		String expectedHeaderValue = "application/json; charset=utf-8";

		switch (framework) {
		case REST_ASSURED:
			Response response = usersWsFacade.getUsersRA();
			Headers responseHeaders = response.getHeaders();

			assertTrue(responseHeaders.hasHeaderWithName(HttpHeaders.CONTENT_TYPE));
			assertEquals(responseHeaders.get(HttpHeaders.CONTENT_TYPE).getValue(), expectedHeaderValue);
			break;
		case REST_TEMPLATE:
			ResponseEntity responseEntity = usersWsFacade.getUsersRT();
			HttpHeaders headers = responseEntity.getHeaders();
			assertTrue(headers.containsKey(HttpHeaders.CONTENT_TYPE));
			assertEquals(headers.getFirst(HttpHeaders.CONTENT_TYPE), expectedHeaderValue);
			break;
		default:
			throw new IllegalArgumentException("No such framework is found " + framework.name());
		}
	}

	@Test(dataProvider = "frameworkDataProvider")
	public void validateResponseBody(Frameworks framework) {
		User[] users;
		switch (framework) {
		case REST_ASSURED:
			Response response = usersWsFacade.getUsersRA();
			users = response.as(User[].class);
			break;
		case REST_TEMPLATE:
			ResponseEntity responseEntity = usersWsFacade.getUsersRT();
			users = (User[]) responseEntity.getBody();
			break;
		default:
			throw new IllegalArgumentException("No such framework is found " + framework.name());
		}

		int expectedNumberOfUsers = 10;
		assertEquals(users.length, expectedNumberOfUsers);

		for (User user : users) {
			assertNotNull(user.getEmail());
			assertTrue(user.getEmail().matches(EMAIL_PATTERN));
			assertNotNull(user.getName());
			assertNotNull(user.getAddress().getZipcode());
		}
	}

	@Test
	public void deleteUser() {
		User user = new User();
		int numberOfRecords = usersWsFacade.getUsersRA().as(User[].class).length;

		//setting id of existing record
		user.setId(getRandomInt(1, numberOfRecords));

		Response response = usersWsFacade.deleteUser(user);
		assertEquals(response.getStatusCode(), HttpStatus.OK.value());
	}

	@DataProvider
	public Object[][] frameworkDataProvider() {
		return new Object[][] {
				{ REST_ASSURED },
				{ REST_TEMPLATE }
		};
	}
}
