package com.kisara.smartcampusapi.service;

import com.kisara.smartcampusapi.model.Room;
import com.kisara.smartcampusapi.exception.RoomNotEmptyException;

import java.util.*;

public class RoomService {
    
    private static Map<String, Room> rooms = new HashMap<>();
    
    // Add room
    public Room addRoom(Room room){
        rooms.put(room.getId(), room);
        return room;
    }
    
    // Get all rooms
    public Collection<Room> getAllRooms(){
        return rooms.values();
    }
    
    // Get one room
    public Room getRoom(String id){
        return rooms.get(id);
    }
    
    // Update room
    public Room updateRoom(String id, Room room){
        rooms.put(id, room);
        return room;
    }
    
    // Delete room
    public boolean deleteRoom(String id){
        Room room = rooms.get(id);
        
        if(room == null){
            return false;
        }
        
        // check the sesnor exist
        if(room.getSensorIds() != null && !room.getSensorIds().isEmpty()){
            throw new RoomNotEmptyException("Room has active sensors");
        }
        
        rooms.remove(id);
        return true;
    }
    
}
