package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;

public class JsonDataDeserializer {

    /**
     * Deserializes json response to model object
     *
     * @param response - json response
     * @param modelClass - class to deserialize to
     * @return - model class object
     */
    public static <T> Object deserializeFromJson(Response response, Class<T> modelClass) {
        return response.getBody().as(modelClass);
    }
}
