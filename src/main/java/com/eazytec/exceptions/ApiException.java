package com.eazytec.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *  Runtime WebApplicationException that is the superclass of all BPM API related exceptions.
 * @author revathi
 *
 */
public class ApiException  extends WebApplicationException{
	
	private static final long serialVersionUID = 1L;
	
	public ApiException(){
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
	}
	
	public ApiException(String message){
		super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
	
	public ApiException(int code,String message){
		super(Response.status(code).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
	
	
}
