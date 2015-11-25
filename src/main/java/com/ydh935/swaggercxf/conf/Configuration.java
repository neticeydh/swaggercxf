package com.ydh935.swaggercxf.conf;

public class Configuration {
	private static Configuration _instance = new Configuration();
	
	private int port;
	private String resources;
	private String scanPackage;
	private String appURLPrefix;
	
	public Configuration(){
		init();
	}
	
	private void init(){
		//
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}
	
	public static Configuration getInstance(){
		return _instance;
	}

	public String getAppURLPrefix() {
		return appURLPrefix;
	}

	public void setAppURLPrefix(String appURLPrefix) {
		this.appURLPrefix = appURLPrefix;
	}
	
	
}
