package com.kisara.smartcampusapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Map;
import java.util.HashMap;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable ex){
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal server error");
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
