package com.kunitskaya.webservices;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.test.webservices.WsFacade;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class WsBaseTest {
    protected ConfigProvider configProvider = ConfigProvider.getInstance();
    protected WsFacade wsFacade = new WsFacade();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = configProvider.getBaseWsTestUrl();
    }
}
