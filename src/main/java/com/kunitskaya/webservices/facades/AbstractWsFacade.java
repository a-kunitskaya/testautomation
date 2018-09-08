package com.kunitskaya.webservices.facades;

import com.kunitskaya.base.ConfigProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class AbstractWsFacade {
    protected static ConfigProvider configProvider = ConfigProvider.getInstance();
    protected static final String BASE_URL = configProvider.getBaseWsTestUrl();
    protected static final String SEPARATOR = "/";

    /**
     * Makes GET request
     * Uses RestAssured framework
     *
     * @param uri - uri to append to base url
     * @return - response
     */
    public Response get(String uri) {
        return RestAssured.when()
                          .get(uri)
                          .andReturn();

    }

    /**
     * Makes GET request
     * Uses RestTemplate framework
     *
     * @param url        - url to make request to
     * @param modelClass - model class to deserialize to
     * @return - response
     */
    public <T> ResponseEntity<T> get(String url, Class<T> modelClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, modelClass);
    }

    /**
     * Makes DELETE request to the specified uri
     * Uses RestAssured framework
     *
     * @param uri - uri to append to bae url
     * @return - response
     */
    public Response delete(String uri) {
        return RestAssured.when().delete(uri)
                          .andReturn();
    }


    /**
     * Makes PUT request
     * Uses RestAssured framework
     *
     * @param uri         - uri to append to base url
     * @param parameters  - json key:value to pass in request
     * @param contentType - content type request header
     * @return - response
     */
    public Response put(String uri, Map<String, String> parameters, ContentType contentType) {
        return RestAssured.given()
                          .when()
                          .contentType(contentType)
                          .body(parameters)
                          .put(uri)
                          .andReturn();
    }

    /**
     * Makes POST request
     * Uses RestAssured framework
     *
     * @param uri         - uri to append to base url
     * @param parameters  - json key:value to pass in request
     * @param contentType - content type request header
     * @return - response
     */
    public Response post(String uri, Map<String, String> parameters, ContentType contentType) {
        return RestAssured.given()
                          .when()
                          .contentType(contentType)
                          .body(parameters)
                          .post(uri)
                          .andReturn();
    }
}
