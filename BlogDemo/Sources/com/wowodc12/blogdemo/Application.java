package com.wowodc12.blogdemo;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyGlobalID;
import com.webobjects.eocontrol.EOObjectStoreCoordinator;
import com.webobjects.eocontrol.EOQualifierEvaluation;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelector;
import com.webobjects.foundation.NSTimestamp;
import com.wowodc12.blogdemo.enums.SyncInfoStatus;
import com.wowodc12.blogdemo.model.Author;
import com.wowodc12.blogdemo.model.BlogEntry;
import com.wowodc12.blogdemo.model.Category;
import com.wowodc12.blogdemo.model.DelegatePKHistory;
import com.wowodc12.blogdemo.model.SyncInfo;
import com.wowodc12.blogdemo.rest.controllers.BlogEntriesController;
import com.wowodc12.blogdemo.rest.controllers.HtmlRoutesController;

import er.extensions.appserver.ERXApplication;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEnterpriseObject;
import er.extensions.foundation.ERXArrayUtilities;
import er.extensions.foundation.ERXRandomGUID;
import er.rest.ERXRestContext;
import er.rest.ERXRestNameRegistry;
import er.rest.IERXRestDelegate;
import er.rest.routes.ERXRoute;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		/* ** put your initialization code in here ** */
		setAllowsConcurrentRequestHandling(true);

		//Define alias. We want to use /ra/posts rather than /ra/blogEntries
    ERXRestNameRegistry.registry().setExternalNameForInternalName("Post", BlogEntry.ENTITY_NAME);

		ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
  
		restRequestHandler.addDefaultRoutes(Author.ENTITY_NAME);		 
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);    
    restRequestHandler.addDefaultRoutes(Category.ENTITY_NAME); 

    //Define default page to show when app starts. See also HtmlRoutesController.indexAction
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/", ERXRoute.Method.Get, HtmlRoutesController.class, "index"));
    //Add routes because BlogEntries no longer has a numeric primary key (as specified in BlogEntryRestDelegate). Note that we insertRoute rather than addRoute
    //so that these routes precede the routes added by restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME) above.
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts/{uniqueTitle:String}", ERXRoute.Method.Get, BlogEntriesController.class, "show"));
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts/{uniqueTitle:String}", ERXRoute.Method.Put, BlogEntriesController.class, "update"));
    restRequestHandler.insertRoute(new ERXRoute(BlogEntry.ENTITY_NAME, "/posts/{uniqueTitle:String}", ERXRoute.Method.Delete, BlogEntriesController.class, "destroy"));
    
		ERXRouteRequestHandler.register(restRequestHandler);
		setDefaultRequestHandler(restRequestHandler);

	}

  @Override
  public void didFinishLaunching() {
    super.didFinishLaunching();
    NSSelector selector = new NSSelector("coordinateChanges", new Class[] { NSNotification.class } );
    NSNotificationCenter.defaultCenter().addObserver( this, selector, EOObjectStoreCoordinator.ObjectsChangedInStoreNotification, EOObjectStoreCoordinator.defaultCoordinator());
  }
  
  @SuppressWarnings("unchecked")
  public void coordinateChanges(NSNotification notification) {
    NSDictionary userInfo = (NSDictionary)notification.userInfo();
    EOEditingContext ec = ERXEC.newEditingContext(new EOObjectStoreCoordinator());
    ec.lock();

    try {

      // ------ We got new EOs, so let's create a new SyncInfo object -----
      NSArray<Object> insertedObjects = ERXArrayUtilities.filteredArrayWithQualifierEvaluation((NSArray<Object>)userInfo.objectForKey("inserted"), new EOSyncEntityFilter() );
      for ( Object id : insertedObjects ) {
        ERXEnterpriseObject eo = eo(id, ec);
        SyncInfo syncDetail = SyncInfo.createSyncInfo(ec, ERXRandomGUID.newGid(), new NSTimestamp(), SyncInfoStatus.INSERTED, eo.entityName() + ":" + eo.primaryKey());
        syncDetail.setDelegatedPrimaryKeyValue((String)entityId(eo));
      }
      
      // ------ We got deleted EOs, so let's fetch the SyncInfo object and set its state to DELETED -----
      NSArray<Object> deletedObjects = ERXArrayUtilities.filteredArrayWithQualifierEvaluation((NSArray<Object>)userInfo.objectForKey("deleted"), new EOSyncEntityFilter() );
      for ( Object id : deletedObjects ) {
        ERXEnterpriseObject eo = eo(id, ec);
        if (eo != null) {
          SyncInfo syncDetail = SyncInfo.fetchSyncInfo(ec, SyncInfo.TOKEN.eq(eo.entityName() + ":" + eo.primaryKey()));
          if (syncDetail != null) {
            syncDetail.setEtag(ERXRandomGUID.newGid());
            syncDetail.setLastModified(new NSTimestamp());
            syncDetail.setState(SyncInfoStatus.DELETED);
          }
        }
      }
      
      // ------ Update EO. We will add a new history object, and update the syncinfo object -----
      NSArray<Object> updatedObjects = ERXArrayUtilities.filteredArrayWithQualifierEvaluation((NSArray<Object>)userInfo.objectForKey("updated"), new EOSyncEntityFilter() );
      for ( Object id : updatedObjects ) {
        ERXEnterpriseObject eo = eo(id, ec);
        if (eo != null) {
          SyncInfo syncDetail = SyncInfo.fetchSyncInfo(ec, SyncInfo.TOKEN.eq(eo.entityName() + ":" + eo.primaryKey()));
          if (syncDetail != null) {
            if (!(syncDetail.delegatedPrimaryKeyValue().equals((String)entityId(eo)))) {
              DelegatePKHistory.createDelegatePKHistory(ec, syncDetail.delegatedPrimaryKeyValue(), syncDetail);
            }
            syncDetail.setEtag(ERXRandomGUID.newGid());
            syncDetail.setLastModified(new NSTimestamp());
            syncDetail.setState(SyncInfoStatus.UPDATED);
            syncDetail.setDelegatedPrimaryKeyValue((String)entityId(eo));
          }
        }
      }

      ec.saveChanges();
    }
    finally {
      ec.unlock();
    }
  }
  
  private ERXEnterpriseObject eo(Object id, EOEditingContext ec) {
    EOKeyGlobalID gid = (EOKeyGlobalID)id;  
    ERXEnterpriseObject eo = (ERXEnterpriseObject)ec.faultForGlobalID( gid, ec);
    return eo;
  }
  
  private Object entityId(ERXEnterpriseObject eo) {
    return IERXRestDelegate.Factory.delegateForEntityNamed(eo.entityName()).primaryKeyForObject(eo, new ERXRestContext(eo.editingContext()));
  }
  
  public class EOSyncEntityFilter implements EOQualifierEvaluation
  {
        public boolean evaluateWithObject(Object object) 
        {
          EOKeyGlobalID eokgid = (EOKeyGlobalID)object;
            return syncEntityNames().containsObject(eokgid.entityName());
        }
  }
  
  public NSArray<String> syncEntityNames() {
    return new NSArray<String>( BlogEntry.ENTITY_NAME );
  }
}
