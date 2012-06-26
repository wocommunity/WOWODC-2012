package com.wowodc12.blogdemo;

import er.extensions.appserver.ERXApplication;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		/* ** put your initialization code in here ** */
		setAllowsConcurrentRequestHandling(true);
		
		ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
		ERXRouteRequestHandler.register(restRequestHandler);
		setDefaultRequestHandler(restRequestHandler);

	}
}
