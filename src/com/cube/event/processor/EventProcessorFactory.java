
package com.cube.event.processor;

import java.lang.reflect.Method;

import com.cube.entity.Event;

public class EventProcessorFactory {

	public static void callRelevantProcessor(Event event) throws Exception {

		try {
			Class serviceClass = Class.forName(EventProcessors.fromString(event.getNoun()).getValue());
			Method method = serviceClass.getDeclaredMethod(event.getVerb(), Event.class);
			Object serviceInstance = serviceClass.newInstance();
			method.invoke(serviceInstance, event);
		} catch (Exception e) {
			System.out.println("Error in event processing");
			throw e;
		}
	}
}
