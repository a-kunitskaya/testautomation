package com.kunitskaya.webservices.deserialization;

import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

import static com.kunitskaya.logging.TestLogger.LOGGER;

/**
 * Created by Alexandra Kunitskaya
 */
public class CustomResponseParser
{
	/**
	 * Parse response
	 *
	 * @param response   - restAssured response
	 * @param modelClass - class to deserialize to
	 * @return instance of the modelClass with fields populated from response
	 */
	public <T> T parseResponse(Response response, Class<T> modelClass) throws IllegalAccessException, InstantiationException
	{
		String body = response.getBody().asString();

		T instance = modelClass.newInstance();
		Field[] fields = instance.getClass().getDeclaredFields();

		for (Field field : fields)
		{
			field.setAccessible(true);
			String value = StringUtils.substringBetween(body, "\"" + field.getName() + "\": \"", "\"");

			try
			{
				field.set(instance, value);
			}
			catch (IllegalAccessException e)
			{
				LOGGER.error("Could not set value " + value + " to field " + field.getName() + e.getMessage());
			}
		}
		return instance;
	}
}
