//Configuration of the front end framework Jersey!
package rrs.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")//The app will look for this in the URL
public class RESTAppConfig extends ResourceConfig{
	//if it finds then it will look for the appropriate class. 
	public RESTAppConfig () {
		packages("rrs.rest");
	}

}
