package com.kisara.smartcampusapi.service;

import com.kisara.smartcampusapi.model.Room;

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
    public void deleteRoom(String id){
        rooms.remove(id);
    }
    
}
