
package com.cube.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cube.rule.Rule;
/**
 * This is Singleton class which keeps all action definitions loaded, thus avoiding loading from DB again and again 
 * @author mohit
 *
 */
public class ActionDefinitionSingleton {

	private ActionDefinition[] definitions;
	private static ActionDefinitionSingleton uniqueInstance = null;

	public static ActionDefinitionSingleton getInstance() {

		if (uniqueInstance == null)
			uniqueInstance = new ActionDefinitionSingleton();
		return uniqueInstance;
	}

	ActionDefinitionSingleton() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = (Query) em.createQuery("SELECT ad FROM com.cube.action.ActionDefinition ad");
		List<Rule> list = (List<Rule>) ((javax.persistence.Query) query).getResultList();
		definitions = list.toArray(new ActionDefinition[0]);
	}

	public ActionDefinition[] getDefinitions() {

		return definitions;
	}
}
