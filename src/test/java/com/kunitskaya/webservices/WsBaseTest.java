package com.kunitskaya.webservices;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.test.webservices.WsFacade;
import io.restassured.RestAssured;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;

public class WsBaseTest {
    protected ConfigProvider configProvider;
    protected WsFacade wsFacade;
    protected RestTemplate restTemplate;

    @BeforeClass
    public void setUp() {
        configProvider = ConfigProvider.getInstance();
        wsFacade = new WsFacade();
        restTemplate = new RestTemplate();
        RestAssured.baseURI = configProvider.getBaseWsTestUrl();
    }
}
