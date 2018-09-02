package com.kunitskaya.webservices;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.test.webservices.WsFacade;
import io.restassured.RestAssured;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class WsBaseTest {
    protected ConfigProvider configProvider;
    protected WsFacade wsFacade;
    protected RestTemplate restTemplate;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        configProvider = ConfigProvider.getInstance();
        wsFacade = new WsFacade();
        restTemplate = new RestTemplate();
        softAssert = new SoftAssert();
        RestAssured.baseURI = configProvider.getBaseWsTestUrl();

    }

    @AfterClass
    public void tearDown() {
        softAssert.assertAll();
    }
}
