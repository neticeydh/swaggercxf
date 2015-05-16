package com.ydh935.swaggercxf.resources;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.wordnik.swagger.jaxrs.listing.ApiListingResource;

@Path("/api-docs")
public class ApiListingResourceJSON extends ApiListingResource{
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApiListingJson(@Context Application app,
			@Context ServletConfig sc, @Context HttpHeaders headers,
			@Context UriInfo uriInfo) {
		return this.getListingJson(app, sc, headers, uriInfo);		
	}
}
