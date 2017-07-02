package com.cube.event.processor;

import com.cube.entity.Event;
import com.cube.entity.EventProcessingResponse;

/**
 * This calls has methods required for processing of events related to bill
 * 
 * @author mohit
 *
 */
public class BillEventProcessor {

	public EventProcessingResponse pay(Event event) {
		System.out.println("Bill Paid");
		return null;
	}

}
