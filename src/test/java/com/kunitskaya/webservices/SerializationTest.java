package com.kunitskaya.webservices;

import com.kunitskaya.base.test.TestDataProvider;
import com.kunitskaya.webservices.models.Gps;
import com.kunitskaya.webservices.serialization.JsonDataSerializer;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;

import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.testng.AssertJUnit.assertEquals;

public class SerializationTest extends WsBaseTest {

    @Test
    @Description("Serialized a model containing primitives to json")
    public void serializationTest() {
        Gps expectedGps = TestDataProvider.getDefaultGps();
        String serialized = JsonDataSerializer.serializeToJson(expectedGps);

        double actualLongitude = parseDouble(substringBetween(serialized, "\"longitude\":", ","));
        double actualLatitude = parseDouble(substringBetween(serialized, "\"latitude\":", "}"));
        Gps actualGps = new Gps(actualLongitude, actualLatitude);

        assertEquals(actualGps, expectedGps);


    }
}
