package com.kunitskaya.webservices.deserialization;

import com.kunitskaya.webservices.models.ToDo;
import cucumber.deps.com.thoughtworks.xstream.core.util.Fields;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

public class ToDoResponseParser implements CustomResponseParser
{
	@Override
	public ToDo parseResponse(Response response)
	{
		String body = response.getBody().asString();

		ToDo toDo = new ToDo();
		Field[] fields = toDo.getClass().getDeclaredFields();
		List<String> values = new ArrayList<>();

		for (Field field : fields)
		{
			field.setAccessible(true);
			String value = StringUtils.substringBetween(body, "\"" + field.getName() + "\": \"", "\"");
			values.add(value);
			try
			{
				field.set(toDo, value);
			}
			catch (IllegalAccessException e)
			{
				TEST_LOGGER.error("Could not set value " + value + " to field " + field.getName() + e.getMessage());
			}
		}
		return toDo;
	}
}
