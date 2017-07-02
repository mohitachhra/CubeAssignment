
package com.cube.event.processor;

public enum EventProcessors {
	bill("com.cube.event.processor.BillEventProcessor"), fdbk("com.cube.event.processor.FeedbackEventProcessor");

	private String value = null;

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	private EventProcessors(String value) {
		this.value = value;
	}

	private EventProcessors() {
		this.value = null;
	}

	public static EventProcessors fromString(String str) {

		for (EventProcessors element : EventProcessors.values()) {
			if (element.toString().equals(str)) {
				return element;
			}
		}
		return null;
	}
}
