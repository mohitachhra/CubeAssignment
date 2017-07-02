
package com.cube.rule;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Rule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String ruleId;
	private String query;
	/*
	 * action to be performed like sending push notification
	 */
	private String action;

	private transient String executeOn;
	// @OneToMany(targetEntity=Expression.class)
	private transient List<Expression> expressions;
	// @OneToMany(targetEntity=Operator.class)
	private transient List<Operator> expOperator;
	private transient List<String> paramters;

	public String getExecuteOn() {

		return executeOn;
	}

	public void setExecuteOn(String executeOn) {

		this.executeOn = executeOn;
	}

	public String getAction() {

		return action;
	}

	public void setAction(String action) {

		this.action = action;
	}

	public List<Expression> getExpressions() {

		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {

		this.expressions = expressions;
	}

	public List<Operator> getExpOperator() {

		return expOperator;
	}

	public void setExpOperator(List<Operator> expOperator) {

		this.expOperator = expOperator;
	}

	public String getQuery() {

		return query;
	}

	public void setQuery(String query) {

		this.query = query;
	}

	public List<String> getParamters() {

		return paramters;
	}

	public void setParamters(List<String> paramters) {

		this.paramters = paramters;
	}

	public String getRuleId() {

		return ruleId;
	}

	public void setRuleId(String ruleId) {

		this.ruleId = ruleId;
	}
}
