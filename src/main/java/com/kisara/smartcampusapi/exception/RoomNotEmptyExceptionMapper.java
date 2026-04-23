package com.kisara.smartcampusapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Map;
import java.util.HashMap;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException>{
    @Override
    public Response toResponse(RoomNotEmptyException ex){
        
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        
        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
