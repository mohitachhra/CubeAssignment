
package com.cube;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cube.action.ActionLogger;
import com.cube.deserializer.EventDeserializer;
import com.cube.entity.Event;
import com.cube.entity.EventProcessingResponse;
import com.cube.event.processor.EventProcessorFactory;
import com.cube.rule.RuleService;
import com.google.gson.GsonBuilder;

public class EventIngestor {
	/**
	 * This method  event in form of Json string.
	 * 1) Its deserializes Json
	 * 2) Calls EventProcessorFactory, so that relevant processor is called
	 * 3) Persists event
	 * 4) calls rule framework to execute rule
	 * 5) calls ActionLogger to log corresponding action
	 * @param json
	 * @return
	 */
	public EventProcessingResponse processEvent(String json) {

		EventProcessingResponse response = new EventProcessingResponse();
		Event event = (Event) deserializeJson(json, Event.class);
		if (event == null) {
			System.out.println("Error in processing. Event is null");
		}
		try {
			EventProcessorFactory.callRelevantProcessor(event);
			persist(event);
			new RuleService().executeRules(event);
			new ActionLogger().logAction(event);
			response.setStatus(true);
		} catch (Exception e) {
			response.setStatus(false);
			System.out.println("Error occured while processing event");
		}
		return response;
	}

	static void persist(Event event) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
		em.close();
	}

	public static Event deserializeJson(String json, Class clazz) {

		GsonBuilder gsonBldr = new GsonBuilder();
		gsonBldr.registerTypeAdapter(Event.class, new EventDeserializer());
		Event targetObject = gsonBldr.create().fromJson(json, Event.class);
		return targetObject;
	}
}
