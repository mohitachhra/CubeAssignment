
package com.cube.action;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Definition of Action configured by cube operator
 * @author mohit
 *
 */
@Entity
@Table
public class ActionDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int aDefId;
	private String query;
	private int executeAfter;
	/**
	 * activity to be performed. like send alert 
	 */
	private String activity;
	/**
	 * comma separated list of query paramters
	 */
	private String queryParams;

	ActionDefinition() {
	}

	ActionDefinition(String query, int executeAfter) {
		this.query = query;
		this.executeAfter = executeAfter;
	}

	public int getaDefId() {

		return aDefId;
	}

	public void setaDefId(int aDefId) {

		this.aDefId = aDefId;
	}

	public String getQuery() {

		return query;
	}

	public void setQuery(String query) {

		this.query = query;
	}

	public int getExecuteAfter() {

		return executeAfter;
	}

	public void setExecuteAfter(int executeAfter) {

		this.executeAfter = executeAfter;
	}

	public String getActivity() {

		return activity;
	}

	public void setActivity(String activity) {

		this.activity = activity;
	}

	public String getQueryParams() {

		return queryParams;
	}

	public void setQueryParams(String queryParams) {

		this.queryParams = queryParams;
	}
}
