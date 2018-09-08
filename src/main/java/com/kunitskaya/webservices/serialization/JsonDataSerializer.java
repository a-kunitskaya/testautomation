package com.kunitskaya.webservices.serialization;

import com.google.gson.Gson;

public class JsonDataSerializer {

    /**
     * Serializes a model containing primitive types to json
     *
     * @param model - model instance to serialize from
     */
    public static String serializeToJson(Object model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}