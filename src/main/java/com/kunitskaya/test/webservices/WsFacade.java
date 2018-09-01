package com.kunitskaya.test.webservices;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WsFacade {

    /**
     * Makes GET request to the specified uri
     * Uses ReastAssured framework
     *
     * @param uri - uri to make get to
     * @return - response object
     */
    public Response get(String uri) {
        return RestAssured.when()
                          .get(uri)
                          .andReturn();
    }

    public ResponseEntity get(String host, String uri, Object[] model){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(host + uri, model.getClass());
    }

    /**
     * Serializes a model containing primitive types to json
     *
     * @param model - model instance to serialize from
     */
    public String serializeToJson(Object model){
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
