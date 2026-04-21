package com.kisara.smartcampusapi.resource;

import com.kisara.smartcampusapi.model.Room;
import com.kisara.smartcampusapi.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    
    private RoomService roomService = new RoomService();
    
    // GET all rooms
    @GET
    public Collection<Room> getRooms(){
        return roomService.getAllRooms();
    }
    
    // GET room by id
    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id){
        return roomService.getRoom(id);
    }
    
    // POST create room
    @POST
    public Room addRoom(Room room){
        return roomService.addRoom(room);
    }
    
    // PUT update room
    @PUT
    @Path("/{id}")
    public Room updateRoom(@PathParam("id") String id, Room room){
        return roomService.updateRoom(id, room);
    }
    
    // DELETE room
    @DELETE
    @Path("/{id}")
    public void deleteRoom(@PathParam("id") String id){
        roomService.deleteRoom(id);
    }
    
}
