package com.kunitskaya.test.webservices;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WsFacade {

    /**
     * Makes GET request to the specified uri
     *
     * @param uri - uri to make get to
     * @return - response object
     */
    public Response get(String uri) {
        return RestAssured.when()
                          .get(uri)
                          .andReturn();
    }
}
