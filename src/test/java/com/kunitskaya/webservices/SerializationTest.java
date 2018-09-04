package com.kunitskaya.webservices;

import com.kunitskaya.webservices.models.Gps;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;

public class SerializationTest extends WsBaseTest {

    @Test
    @Description("Serialized a model containing primitives to json")
    public void serializationTest() {
        double longitude = 40.9026;
        double latitude = 174.8865;

        String serialized = wsFacade.serializeToJson(new Gps(longitude, latitude));

        String expectedJsonLongitude = "\"longitude\":" + longitude;
        String expectedJsonLatitude = "\"latitude\":" + latitude;

        softAssert.assertTrue(serialized.contains(expectedJsonLongitude + "," + expectedJsonLatitude));
    }
}
