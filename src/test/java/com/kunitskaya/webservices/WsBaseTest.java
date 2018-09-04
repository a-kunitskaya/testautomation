package com.kunitskaya.webservices;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.test.webservices.UsersWsFacade;
import io.restassured.RestAssured;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class WsBaseTest {
    protected ConfigProvider configProvider;
    protected UsersWsFacade usersWsFacade;
    protected RestTemplate restTemplate;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        configProvider = ConfigProvider.getInstance();
        usersWsFacade = new UsersWsFacade();
        restTemplate = new RestTemplate();
        softAssert = new SoftAssert();
        RestAssured.baseURI = configProvider.getBaseWsTestUrl();
    }

    @AfterClass
    public void tearDown() {
        softAssert.assertAll();
    }
}
