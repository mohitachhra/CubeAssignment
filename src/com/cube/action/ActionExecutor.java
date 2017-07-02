
package com.cube.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.cube.service.AlertCubeOperator;
import com.cube.service.AlertUserService;
import com.cube.service.PushNotification;

/**
 * This class is invoked at startup of Tomcat Server. This is a thread which
 * keeps executing every 30 sec. Its loads eligible actions depending on pickup
 * time, and execute the activities as defined
 * 
 * @author mohit
 *
 */
public class ActionExecutor extends HttpServlet implements Runnable {

	Map paramMap = new HashMap<>();

	public void run() {

		while (true) {
			try {
				Thread.sleep(30000);
				System.out.println("Executor Invoked");
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
				EntityManager em = factory.createEntityManager();
				em.getTransaction().begin();
				Timestamp currentTS = new Timestamp(System.currentTimeMillis());
				Query query = (Query) em.createQuery(
						"SELECT action FROM com.cube.action.Action action WHERE action.isActive='1' and action.pickUpTime <= :currentTS");
				List<Action> list = (List<Action>) ((javax.persistence.Query) query)
						.setParameter("currentTS", currentTS).getResultList();
				Action actions[] = list.toArray(new Action[0]);
				for (Action action : actions) {
					action.setActive(false);
					setParamMap(action);
					try {
						ActionDefinition actionDefinition = em.find(ActionDefinition.class, action.getActionDefId());
						Query actionquery = (Query) em.createQuery(actionDefinition.getQuery());
						String param[] = actionDefinition.getQueryParams().split(",");
						for (String parameter : param) {
							((javax.persistence.Query) actionquery).setParameter(parameter, paramMap.get(parameter));
						}
						List result = ((javax.persistence.Query) actionquery).getResultList();
						if (!result.isEmpty()) {
							executeAction(actionDefinition.getActivity());
						}
					} catch (Exception e) {
						System.out.println("----Error in Action Execution----");
						e.printStackTrace();
					}
					em.persist(action);
				}
				em.getTransaction().commit();
				em.close();
			} catch (Exception e) {
				System.out.println("Error in ActionExecutor");
				e.printStackTrace();
			}
		}
	}

	private void setParamMap(Action action) {

		paramMap.put("TriggeringEventId", action.getTriggeringEventId());
		paramMap.put("CreationTime", action.getCreationTime());
		paramMap.put("TriggeringUserId", action.getTriggeringUserId());
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

	public void init() throws ServletException {

		ActionExecutor executor = new ActionExecutor();
		Thread t = new Thread(executor);
		t.start();
	}
}
