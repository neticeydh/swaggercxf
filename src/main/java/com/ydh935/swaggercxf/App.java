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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.wordnik.swagger.config.ScannerFactory;
import com.ydh935.swaggercxf.conf.Configuration;
import com.ydh935.swaggercxf.resources.LocalDefaultJaxrsScanner;


/**
 * Hello world!
 * Path List:
 *      /swagger            ----swagger ui resources
 *      /app/swagger        ----swagger
 *      /app/api-docs       ----swagger api list
 *      
 * JSP is not good for dynamic swagger ui. It's need tmp dir.
 *      
 */
public class App 
{
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	private final static String SWAGGER_CXF_APP = "/app";
	
	private int port;
	private String resourceClasses = "com.ydh935.swaggercxf.resources.ApiListingResourceJSON,com.ydh935.swaggercxf.resources.SwaggerResource";
	
	public App(){
		this(8080);
	}
	
	public App(int port){
		this.port = port;
	}
	
	public void registerResource(String resourceClass){
		this.resourceClasses += ("," + resourceClass);
	}
	
	public void start(){
		try{
			//configuration
			Configuration.getInstance().setPort(port);
			Configuration.getInstance().setResources("/swagger/");
			Configuration.getInstance().setAppURLPrefix(SWAGGER_CXF_APP);
			
	    	// Create a basic Jetty server object that will listen on port 8080.  .
	        Server server = new Server(port);
	 
	        // Create the ResourceHandler. It is the object that will actually handle the request for swagger ui.
	        ResourceHandler resource_handler = new ResourceHandler();
	        
	        resource_handler.setDirectoriesListed(true);
	        //resource_handler.setWelcomeFiles(new String[]{ "swagger.jsp" });
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
	        jaxrsServlet.setInitParameter("jaxrs.serviceClasses", resourceClasses);
	        // JSON Provider
	        jaxrsServlet.setInitParameter("jaxrs.providers", "com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider,com.wordnik.swagger.jaxrs.listing.SwaggerSerializers");
	        // Add to servlet context
	        ServletContextHandler context = new ServletContextHandler(); 
	        context.setContextPath( SWAGGER_CXF_APP );	        
	        context.addServlet( jaxrsServlet, "/*" );
	        
	        // Jetty server handler list
	        handlers.setHandlers(new Handler[] { context, swaggerCtx,  new DefaultHandler() });
	        server.setHandler(handlers);
	        	        
	        ScannerFactory.setScanner(new LocalDefaultJaxrsScanner());
	        
	        logger.info("Starting server");
	        // Start the jetty
	        server.start();
	        logger.info("Server is started");
	        
	        server.join();
    	}catch(Exception e){
    		logger.error("Start Server", e);
    	}
	}
	
    public static void main( String[] args )
    {
    	App app = new App();
    	app.registerResource("com.ydh935.swaggercxf.resources.ContactorResource");
    	app.start();
    }
}
