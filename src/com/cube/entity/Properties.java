package com.cube.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int pid;
	String lhs;
	String rhs;
	int eventId;

	public int getPid() {

		return pid;
	}

	public void setPid(int pid) {

		this.pid = pid;
	}

	public String getLhs() {

		return lhs;
	}

	public void setLhs(String lhs) {

		this.lhs = lhs;
	}

	public String getRhs() {

		return rhs;
	}

	public void setRhs(String rhs) {

		this.rhs = rhs;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
}
