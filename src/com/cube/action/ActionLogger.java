
package com.cube.action;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cube.entity.Event;

/**
 * Executed on every transaction. It picks up actionDefinition and logs
 * corresponding action with appropriate pickup time defined in actionDefinition
 * 
 * @author mohit
 *
 */
public class ActionLogger {

	public void logAction(Event event) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		for (ActionDefinition definition : ActionDefinitionSingleton.getInstance().getDefinitions()) {
			Action action = new Action();
			action.setActionDefId(definition.getaDefId());
			action.setTriggeringEventId(event.getId());
			action.setTriggeringUserId(event.getUserid());
			Timestamp currentTS = new Timestamp(System.currentTimeMillis());
			action.setCreationTime(currentTS);
			Timestamp pickUpTs = (Timestamp) currentTS.clone();
			pickUpTs.setMinutes(currentTS.getMinutes() + definition.getExecuteAfter());
			action.setPickUpTime(pickUpTs);
			em.persist(action);
		}
		em.getTransaction().commit();
		em.close();
	}
}
