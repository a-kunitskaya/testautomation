package com.kunitskaya.webservices;

import com.kunitskaya.test.webservices.WsFacade;
import com.kunitskaya.webservices.models.Gps;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;

public class SerializationTest {

    @Test
    @Description("Serialized a model containing primitives to json")
    public void serializationTest() {
        String serialized = new WsFacade().serializeToJson(new Gps(40.9026, 174.8865));
        System.out.println(serialized);
    }
}
