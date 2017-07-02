
package com.cube.action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Service exposed as API to create actions.
 * 
 * @author mohit
 */
@Path("/ActionDefinitionService")
public class ActionDefinitionService {

	@POST
	@Path("/createAction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createActionDefinition() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(getActionDefinition(1));
		em.persist(getActionDefinition(2));
		em.getTransaction().commit();
		em.close();
		return true;
	}

	private ActionDefinition getActionDefinition(int id) {

		ActionDefinition actionDefinition = new ActionDefinition();
		String query = null;
		if (id == 2) {
			query = "SELECT event FROM com.cube.entity.Event event WHERE event.noun!='fdbk' and event.id=:TriggeringEventId and event.userid = :TriggeringUserId";
			actionDefinition.setExecuteAfter(0);
			actionDefinition.setQuery(query);
			actionDefinition.setActivity("AlertCubeOperator");
			actionDefinition.setQueryParams("TriggeringEventId,TriggeringUserId");
		} else if (id == 1) {
			query = "SELECT SUM(properties.rhs) FROM com.cube.entity.Properties properties, com.cube.entity.Event event WHERE event.noun='bill' and event.verb='pay' and event.userid = :TriggeringUserId AND properties.lhs='value' and properties.eventId=event.id and event.creationTime>=:CreationTime GROUP BY event.userid HAVING SUM(properties.rhs) >= 20000";
			actionDefinition.setExecuteAfter(0);
			actionDefinition.setQuery(query);
			actionDefinition.setActivity("AlertUser");
			actionDefinition.setQueryParams("CreationTime,TriggeringUserId");
		}
		return actionDefinition;
	}
}
