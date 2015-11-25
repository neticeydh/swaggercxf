package com.ydh935.swaggercxf.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.ydh935.swaggercxf.model.Contactor;

@Path("/contacts")
@Api("/contacts")
public class ContactorResource {

	@GET
	@ApiOperation("Contacts endpoint")
	@Produces( { "application/json" } )
	public List<Contactor> list(){
		List<Contactor> cList = new ArrayList<Contactor>();
		Contactor ct = new Contactor();
		ct.setName("tester");
		ct.setEmail("tester@mycompany.com");
		ct.setPhone("34536547");
		cList.add(ct);
		return cList;
	}
	
	@GET
	@Path("/{name}")
	@ApiOperation("Contacts endpoint with path param")
	@Produces( { "application/json" } )
	public Contactor getContactor(@PathParam("name") String name){
		Contactor ct = new Contactor();
		ct.setName("tester");
		ct.setEmail("tester@mycompany.com");
		ct.setPhone("34536547");
		
		return ct;
	}
}
