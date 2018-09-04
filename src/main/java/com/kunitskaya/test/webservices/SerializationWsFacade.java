package com.kunitskaya.test.webservices;

import com.google.gson.Gson;

public class SerializationWsFacade extends AbstractWsFacade {

    /**
     * Serializes a model containing primitive types to json
     *
     * @param model - model instance to serialize from
     */
    public <T> String serializeToJson(Class<T> model){
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
