package com.cube;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cube.entity.EventProcessingResponse;
/**
 * Service which consumes the events
 * @author mohit
 *
 */
@Path("/EventIngestorService")
public class EventIngestorService {
	@POST
	@Path("/ingestEvent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EventProcessingResponse ingestEvent(String jsonRequest){
		EventIngestor eventIngestor = new EventIngestor();
		return eventIngestor.processEvent(jsonRequest);
	}
}
