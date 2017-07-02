
package com.cube.rule;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * This is Singleton class which keeps all rules loaded, thus
 * avoiding loading from DB again and again
 * 
 * @author mohit
 */

public class RulesSingleton {

	private Rule[] rules;
	private static RulesSingleton uniqueInstance;

	private RulesSingleton() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = (Query) em.createQuery("SELECT rule FROM com.cube.rule.Rule rule");
		List<Rule> list = (List<Rule>) ((javax.persistence.Query) query).getResultList();
		rules = list.toArray(new Rule[0]);
	}

	public static RulesSingleton getUniqueInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new RulesSingleton();
		return uniqueInstance;
	}

	public Rule[] getRules() {

		return rules;
	}
}
