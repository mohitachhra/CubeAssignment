
package com.cube.action;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Action picked up by ActionExecutor depending on pick up time
 * @author mohit
 *
 */
@Entity
@Table
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int actionId;
	private int actionDefId;
	private int triggeringEventId;
	private int triggeringUserId;
	private Timestamp pickUpTime;
	private Timestamp creationTime;
	private boolean isActive=true;

	
	public boolean isActive() {
	
		return isActive;
	}

	
	public void setActive(boolean isActive) {
	
		this.isActive = isActive;
	}

	public int getActionId() {

		return actionId;
	}

	public int getActionDefId() {

		return actionDefId;
	}

	public void setActionDefId(int actionDefId) {

		this.actionDefId = actionDefId;
	}

	public Timestamp getPickUpTime() {

		return pickUpTime;
	}

	public void setPickUpTime(Timestamp pickUpTime) {

		this.pickUpTime = pickUpTime;
	}

	public Timestamp getCreationTime() {

		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {

		this.creationTime = creationTime;
	}

	public int getTriggeringEventId() {

		return triggeringEventId;
	}

	public void setTriggeringEventId(int triggeringEventId) {

		this.triggeringEventId = triggeringEventId;
	}

	public int getTriggeringUserId() {

		return triggeringUserId;
	}

	public void setTriggeringUserId(int triggeringUserId) {

		this.triggeringUserId = triggeringUserId;
	}
}
