
package com.cube.deserializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cube.entity.Event;
import com.cube.entity.Properties;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.StringMap;

public class EventDeserializer implements JsonDeserializer<Event> {

	@Override
	public Event deserialize(JsonElement jElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		Event event = new Event();
		JsonObject jObject = jElement.getAsJsonObject();
		event.setUserid(jObject.get("userid").getAsInt());
		event.setLatlong(jObject.get("latlong").getAsString());
		event.setNoun(jObject.get("noun").getAsString());
		event.setVerb(jObject.get("verb").getAsString());
		event.setTimespent(jObject.get("timespent").getAsInt());
		event.setTs(jObject.get("ts").getAsString());
		List<Properties> list = new ArrayList<>();
		StringMap map = context.deserialize(jObject.get("properties"), Object.class);
		for (Object element : map.keySet()) {
			Properties prop = new Properties();
			prop.setLhs((String) element);
			prop.setRhs((String) map.get(element).toString());
			list.add(prop);
		}
		event.setProperties(list);
		event.setCreationTime(new Timestamp(System.currentTimeMillis()));
		return event;
	}
}
