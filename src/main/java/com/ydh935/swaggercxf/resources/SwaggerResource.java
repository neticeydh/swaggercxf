package com.ydh935.swaggercxf.resources;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@Path("/swagger")
public class SwaggerResource {
	private final static Logger logger = LoggerFactory.getLogger(SwaggerResource.class);
	
	@GET
	public Response getSwaggerView(@Context HttpServletRequest request, @Context HttpServletResponse response){
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);		
		/* Get the template (uses cache internally) */
        try {        	
        	cfg.setDirectoryForTemplateLoading(new ClassPathResource("swaggerui").getFile());
			Template temp = cfg.getTemplate("swagger.ftl");
			ServletOutputStream outputStream = response.getOutputStream();
			Writer out = new OutputStreamWriter(outputStream);
			temp.process(com.ydh935.swaggercxf.conf.Configuration.getInstance(), out);
		}  catch (Exception e) {			
			logger.error("swagger view", e);
		}
        
		return Response.ok().build();
	}
}
