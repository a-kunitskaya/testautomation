package com.kunitskaya.test.webservices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UsersWsFacade extends AbstractWsFacade {


    private String url = baseUrl + configProvider.getWsUsersUri();
    private String uri = configProvider.getWsUsersUri();

    /**
     * Makes GET request to the specified uri
     * Uses RestAssured framework
     *
     * @return - response object
     */
    public Response get() {
        return RestAssured.when()
                          .get(uri)
                          .andReturn();
    }

    public <T> ResponseEntity<T> get(Class<T> modelClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, modelClass);
    }
}
