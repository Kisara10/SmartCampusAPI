package com.kisara.smartcampusapi.service;

import com.kisara.smartcampusapi.model.SensorReading;
import com.kisara.smartcampusapi.model.Sensor;
import com.kisara.smartcampusapi.exception.SensorUnavailableException;

import java.util.*;

public class SensorReadingService {
    
    private static Map<String, List<SensorReading>> readings = new HashMap<>();
    
    private static SensorService sensorService = new SensorService();
    
    // Get readings for a sensor
    public List<SensorReading> getReadings(String sensorId){
        return readings.getOrDefault(sensorId, new ArrayList<>());
    }
    
    // Add reading
    public SensorReading addReading(String sensorId, SensorReading reading){
        Sensor sensor = sensorService.getSensor(sensorId);
        
        if(sensor == null){
            throw new RuntimeException("Sensor not found");
        }
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())){
            throw new SensorUnavailableException("Sensor is under maintenance");
        }
        
        // Add reading to list
        readings.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(reading);
        
        sensor.setCurrentValue(reading.getValue());
        
        return reading;
    }
    
}
