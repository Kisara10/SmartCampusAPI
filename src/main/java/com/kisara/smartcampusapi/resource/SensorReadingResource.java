package com.kisara.smartcampusapi.resource;

import com.kisara.smartcampusapi.model.SensorReading;
import com.kisara.smartcampusapi.service.SensorReadingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    private SensorReadingService readingService = new SensorReadingService();
    private String sensorId;
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
    }
    
    // GET reading
    @GET
    public List<SensorReading> getReadings(){
        return readingService.getReadings(sensorId);
    }
    
    @POST
    public SensorReading addReading(SensorReading reading){
        return readingService.addReading(sensorId, reading);
    }
    
}
