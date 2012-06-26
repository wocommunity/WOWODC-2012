package com.wowodc12.blogdemo;

import com.wowodc12.blogdemo.model.Author;
import com.wowodc12.blogdemo.model.BlogEntry;
import com.wowodc12.blogdemo.model.Category;

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
    restRequestHandler.addDefaultRoutes(Author.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);    
    restRequestHandler.addDefaultRoutes(Category.ENTITY_NAME); 
		ERXRouteRequestHandler.register(restRequestHandler);
		setDefaultRequestHandler(restRequestHandler);

	}
}
