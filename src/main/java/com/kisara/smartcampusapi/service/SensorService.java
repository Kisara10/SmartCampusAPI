package com.kisara.smartcampusapi.service;

import com.kisara.smartcampusapi.model.Sensor;
import com.kisara.smartcampusapi.model.Room;
import com.kisara.smartcampusapi.exception.LinkedResourceNotFoundException;

import java.util.*;

public class SensorService {
    
    private static Map<String, Sensor> sensors = new HashMap<>();
    private RoomService roomService = new RoomService();
    
    // Add Sensor
    public Sensor addSensor(Sensor sensor){
        
        // Check room exists
        Room room = roomService.getRoom(sensor.getRoomId());
        
        if (room == null){
            throw new LinkedResourceNotFoundException("Room does not exist");
        }
        
        // Add sensor
        sensors.put(sensor.getId(), sensor);
        
        // Link sensor to room
        room.getSensorIds().add(sensor.getId());
        
        return sensor;
    }
    
    // Get all sensors
    public Collection<Sensor> getAllSensors(){
        return sensors.values();
    }
    
    // Get sesnor by id
    public Sensor getSensor(String id){
        return sensors.get(id);
    }
    
    // Delete sensor
    public void deleteSensor(String id){
        Sensor sensor = sensors.get(id);
        
        if(sensor != null){
            // Remove from room
            Room room = roomService.getRoom(sensor.getRoomId());
            
            if (room != null){
                room.getSensorIds().remove(id);
            }
            
            sensors.remove(id);
        }
    }
    
    public Collection<Sensor> getSensorsByType(String type){
        List<Sensor> filtered = new ArrayList<>();
        
        for(Sensor sensor : sensors.values()){
            if(sensor.getType() != null && sensor.getType(). equalsIgnoreCase(type)){
                filtered.add(sensor);
            }
        }
        
        return filtered;
    }
    
}
