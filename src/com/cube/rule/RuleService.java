
package com.cube.rule;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cube.entity.Event;
import com.cube.service.AlertCubeOperator;
import com.cube.service.AlertUserService;
import com.cube.service.PushNotification;

/**
 * Service that loads rules from singleton and executes rules on every transaction
 * 
 * @author mohit
 *
 */
@Path("/RuleService")
public class RuleService {

	public void executeRules(Event event) {
		// createHardRule1();
		Rule rules[] = RulesSingleton.getUniqueInstance().getRules();
		for (Rule rule : rules) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
			EntityManager em = factory.createEntityManager();
			Query query = (Query) em.createQuery(rule.getQuery());
			List<Event> todoList = (List<Event>) ((javax.persistence.Query) query)
					.setParameter("userid", event.getUserid()).getResultList();
			if (!todoList.isEmpty()) {
				executeAction(rule.getAction());
			}
		}
	}

	private void executeAction(String action) {

		switch (action) {
		case "PushNotification":
			new PushNotification().execute();
			break;
		case "AlertUser":
			new AlertUserService().execute();
			break;
		case "AlertCubeOperator":
			new AlertCubeOperator().execute();
			break;
		}
	}

	@POST
	@Path("/createRule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Rule createHardRule() {

		Rule rule = new Rule();
		rule.setAction("PushNotification");
		rule.setQuery(
				"SELECT COUNT(event) FROM com.cube.entity.Event event WHERE event.noun='bill' and event.verb='pay' and event.userid = :userid GROUP BY event.userid HAVING COUNT(event)=1");
		LinkedList<Expression> expList = new LinkedList<>();
		LinkedList<Operator> opList = new LinkedList<>();
		LinkedList<String> paramters = new LinkedList<>();
		Expression e1 = new Expression();
		e1.setId(1);
		e1.setLHS("event.noun");
		e1.setOperator("=");
		e1.setRHS("bill");
		expList.add(e1);
		Expression e2 = new Expression();
		e2.setId(2);
		e2.setLHS("event.verb");
		e2.setOperator("=");
		e2.setRHS("pay");
		expList.add(e2);
		Expression e3 = new Expression();
		e3.setId(2);
		e3.setLHS("event.userid");
		e3.setOperator("=");
		e3.setRHS(":userid");
		expList.add(e3);
		rule.setExpressions(expList);
		Operator o = new Operator(1, "AND");
		opList.add(o);
		o = new Operator(2, "AND");
		opList.add(o);
		rule.setExpOperator(opList);
		paramters.add("Event.userId");
		rule.setParamters(paramters);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(rule);
		em.getTransaction().commit();
		em.close();
		return rule;
	}

	public void createRule(Rule rule) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(rule);
		em.getTransaction().commit();
		em.close();
	}
}
