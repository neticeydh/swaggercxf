package com.ydh935.swaggercxf;


import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;

import com.wordnik.swagger.config.ScannerFactory;
import com.ydh935.swaggercxf.resources.LocalDefaultJaxrsScanner;


/**
 * Hello world!
 * Path List:
 *      /swagger        ----swagger ui
 *      /api-docs       ----swagger api list
 *      
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{
	    	// Create a basic Jetty server object that will listen on port 8080.  .
	        Server server = new Server(8080);
	 
	        // Create the ResourceHandler. It is the object that will actually handle the request for swagger ui.
	        ResourceHandler resource_handler = new ResourceHandler();
	        
	        resource_handler.setDirectoriesListed(true);
	        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
	        resource_handler.setResourceBase(new ClassPathResource("swaggerui").getURI().toString());
	        
	        ContextHandler swaggerCtx = new ContextHandler("/swagger"); /* the swagger path */
	        swaggerCtx.setHandler(resource_handler);
	        	        
	        // Add the ResourceHandler to the server.
	        HandlerList handlers = new HandlerList();
	        
	        // CXF servlet handler
	        CXFNonSpringJaxrsServlet cxf = new CXFNonSpringJaxrsServlet();
	        ServletHolder jaxrsServlet = new ServletHolder(cxf);
	        jaxrsServlet.setName("myCXF");
	        // Resource list
	        jaxrsServlet.setInitParameter("jaxrs.serviceClasses", "com.ydh935.swaggercxf.resources.ContactorResource,com.ydh935.swaggercxf.resources.ApiListingResourceJSON");
	        // JSON Provider
	        jaxrsServlet.setInitParameter("jaxrs.providers", "com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider,com.wordnik.swagger.jaxrs.listing.SwaggerSerializers");
	        // Add to servlet context
	        ServletContextHandler context = new ServletContextHandler(); 
	        context.setContextPath( "/" );	        
	        context.addServlet( jaxrsServlet, "/*" );
	        
	        // Jetty server handler list
	        handlers.setHandlers(new Handler[] { swaggerCtx, context, new DefaultHandler() });
	        server.setHandler(handlers);
	 
	        ScannerFactory.setScanner(new LocalDefaultJaxrsScanner());
	        
	        // Start the jetty
	        server.start();
	        
	        server.join();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
