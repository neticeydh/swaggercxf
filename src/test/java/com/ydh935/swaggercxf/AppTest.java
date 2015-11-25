package com.ydh935.swaggercxf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	Thread _t = new Thread(new Runnable(){
			@Override
			public void run() {
				App app = new App(5353);
				app.registerResource("com.ydh935.swaggercxf.resources.ContactorResource");
		    	app.start();				
			}    		
    	});
    	
    	_t.start();
    	
    	WebDriver driver = new FirefoxDriver();
    	driver.get("http://localhost:5353/app/swagger");
    	
        assertTrue( true );
    }
}
