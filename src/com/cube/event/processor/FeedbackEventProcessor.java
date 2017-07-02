package com.cube.event.processor;

import com.cube.entity.Event;
import com.cube.entity.EventProcessingResponse;

/**
 * This calls has methods required for processing of events related to feedback
 * 
 * @author mohit
 *
 */
class FeedbackEventProcessor {

	public EventProcessingResponse post(Event event) {
		System.out.println("Feedback posted");
		return null;
	}
}
