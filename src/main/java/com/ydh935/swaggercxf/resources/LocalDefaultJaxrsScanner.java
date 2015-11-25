package com.ydh935.swaggercxf.resources;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Application;

import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.AbstractScanner;
import com.wordnik.swagger.jaxrs.config.JaxrsScanner;
import com.wordnik.swagger.models.Swagger;
import com.ydh935.swaggercxf.conf.Configuration;

public class LocalDefaultJaxrsScanner extends AbstractScanner implements
		JaxrsScanner, SwaggerConfig {
	private boolean prettyPrint = false;

	public Set<Class<?>> classesFromContext(Application app, ServletConfig sc) {
		Set<Class<?>> output = new HashSet<Class<?>>();
		String resourceClasses = sc.getInitParameter("jaxrs.serviceClasses");
		if (resourceClasses != null) {
			String[] classes = resourceClasses.split(",");
			for(String clz : classes){
				try {
					output.add(Class.forName(clz));
				} catch (ClassNotFoundException e) {					
					e.printStackTrace();
				}
			}			
		}
		return output;
	}

	public Set<Class<?>> classes() {
		return new HashSet<Class<?>>();
	}

	public boolean prettyPrint() {
		return prettyPrint;
	}

	public void setPrettyPrint(boolean shouldPrettyPrint) {
		this.prettyPrint = shouldPrettyPrint;
	}

	@Override
	public Swagger configure(Swagger swagger) {
		// just for baseurl
		swagger.setBasePath(Configuration.getInstance().getAppURLPrefix());
		return swagger;
	}
}
