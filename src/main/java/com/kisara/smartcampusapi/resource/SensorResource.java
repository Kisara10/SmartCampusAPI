package com.kisara.smartcampusapi.resource;

import com.kisara.smartcampusapi.model.Sensor;
import com.kisara.smartcampusapi.service.SensorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    private SensorService sensorService = new SensorService();
    
    // GET all sensors
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type){
        if (type != null && !type.isEmpty()){
            return sensorService.getSensorsByType(type);
        }
        
        return sensorService.getAllSensors();
    }
    
    // POST create sensor
    @POST 
    public Sensor addSensor(Sensor sensor){
        return sensorService.addSensor(sensor);
    }
    
    // GET by id
    @GET
    @Path("/{id}")
    public Sensor getSensor(@PathParam("id") String id){
        return sensorService.getSensor(id);
    }
    
    // DELETE sensor
    @DELETE
    @Path("/{id}")
    public String deleteSensor(@PathParam("id") String id){
        sensorService.deleteSensor(id);
        return "Sensor deleted successfully";
    }
    
    @Path("/{id}/readings")
    public SensorReadingResource getSensorReadings(@PathParam("id") String id){
        return new SensorReadingResource(id);
    }
    
    
}
