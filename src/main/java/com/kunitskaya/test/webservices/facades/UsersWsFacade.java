package com.kunitskaya.test.webservices.facades;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UsersWsFacade extends AbstractWsFacade {
    private String url = baseUrl + configProvider.getWsUsersUri();
    private String uri = configProvider.getWsUsersUri();

    /**
     * Makes GET request
     * Uses RestAssured framework
     *
     * @return - response object
     */
    public Response get() {
        return RestAssured.when()
                          .get(uri)
                          .andReturn();
    }

    /**
     * Makes GET request
     * Uses RestTemplate framework
     *
     * @param modelClass - model class
     * @return - response object
     */
    public <T> ResponseEntity<T> get(Class<T> modelClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, modelClass);
    }

    /**
     * Makes DELETE request to the specified uri
     * Uses RestAssured framework
     *
     * @param id = id of the object to delete
     * @return - response object
     */
    public Response delete(String id) {
        return RestAssured.when().delete(uri + "/" + id)
                          .andReturn();
    }
}
