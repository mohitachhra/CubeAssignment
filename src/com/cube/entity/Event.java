
package com.cube.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String ts;
	private String noun;
	private String verb;
	private String latlong;
	private int timespent;
	private int userid;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = com.cube.entity.Properties.class)
	@JoinColumn(name = "id", referencedColumnName = "eventId")
	private List<Properties> properties;
	private Timestamp creationTime;

	public int getUserid() {

		return userid;
	}

	public void setUserid(int userid) {

		this.userid = userid;
	}

	public String getTs() {

		return ts;
	}

	public void setTs(String ts) {

		this.ts = ts;
	}

	public String getNoun() {

		return noun;
	}

	public void setNoun(String noun) {

		this.noun = noun;
	}

	public String getVerb() {

		return verb;
	}

	public void setVerb(String verb) {

		this.verb = verb;
	}

	public String getLatlong() {

		return latlong;
	}

	public void setLatlong(String latlong) {

		this.latlong = latlong;
	}

	public int getTimespent() {

		return timespent;
	}

	public void setTimespent(int timespent) {

		this.timespent = timespent;
	}

	public Object getProperties() {

		return properties;
	}

	public void setProperties(List<Properties> properties) {

		this.properties = properties;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public Timestamp getCreationTime() {

		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {

		this.creationTime = creationTime;
	}
}
