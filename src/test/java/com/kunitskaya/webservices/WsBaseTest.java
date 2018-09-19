package com.kunitskaya.webservices;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.webservices.facades.ToDoWsFacade;
import com.kunitskaya.webservices.facades.UsersWsFacade;
import io.restassured.RestAssured;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;

import static com.kunitskaya.logging.TestLogger.LOGGER;
import static com.kunitskaya.logging.TestLogger.WS_LOGGER;

public class WsBaseTest {
    protected ConfigProvider configProvider;
    protected UsersWsFacade usersWsFacade;
    protected ToDoWsFacade toDoWsFacade;
    protected RestTemplate restTemplate;


    @BeforeClass
    public void setUp() {
        configProvider = ConfigProvider.getInstance();
        usersWsFacade = new UsersWsFacade();
        toDoWsFacade = new ToDoWsFacade();
        restTemplate = new RestTemplate();
        RestAssured.baseURI = configProvider.getBaseWsTestUrl();
        LOGGER = WS_LOGGER;
    }
}
