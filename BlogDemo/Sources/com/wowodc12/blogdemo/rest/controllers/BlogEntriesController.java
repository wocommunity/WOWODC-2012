package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORedirect;
import com.webobjects.appserver.WORequest;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.foundation.NSTimestampFormatter;
import com.wowodc12.blogdemo.model.BlogEntry;
import com.wowodc12.blogdemo.model.DelegatePKHistory;
import com.wowodc12.blogdemo.model.SyncInfo;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXDatabaseContextDelegate;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;
import er.rest.ERXRestFetchSpecification;
import er.rest.IERXRestDelegate;
import er.rest.routes.ERXRouteUrlUtils;

public class BlogEntriesController extends BaseRestController {

  public BlogEntriesController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    checkSecurity();
    BlogEntry newEntry = create(inFilter());
    editingContext().saveChanges();
    return response(newEntry, outFilter());
  }
  
  @Override
  public WOActionResults showAction() throws Throwable {
    String uniqueTitle = routeObjectForKey("uniqueTitle");
    try {
      BlogEntry entry = blogEntryForUniqueTitle();
      return response(entry, outFilter());
    } catch (ERXDatabaseContextDelegate.ObjectNotAvailableException ex) {
      DelegatePKHistory history = DelegatePKHistory.fetchDelegatePKHistory(editingContext(), DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE.eq(uniqueTitle));
      if (history != null) {
        SyncInfo syncInfo = history.syncInfo();
        if (syncInfo != null) {
          BlogEntry entry = (BlogEntry)IERXRestDelegate.Factory.delegateForEntityNamed(BlogEntry.ENTITY_NAME).objectOfEntityWithID(EOClassDescription.classDescriptionForClass(BlogEntry.class), syncInfo.delegatedPrimaryKeyValue(), _restContext);
          String urlForRedirect = ERXRouteUrlUtils.actionUrlForRecord(this.context(), entry, "show", this.format().name(), null, false, false);
          WORedirect redirect = new WORedirect(this.context());
          redirect.setUrl(urlForRedirect);
          return redirect;
        } else {
          return response(ERXHttpStatusCodes.GONE);
        }
      } else {
        return response(ERXHttpStatusCodes.NOT_FOUND);
      }
    }
  }
  
  public BlogEntry blogEntryForUniqueTitle() {
    String uniqueTitle = routeObjectForKey("uniqueTitle");
    BlogEntry entry = (BlogEntry)IERXRestDelegate.Factory.delegateForEntityNamed(BlogEntry.ENTITY_NAME).objectOfEntityWithID(EOClassDescription.classDescriptionForClass(BlogEntry.class), uniqueTitle, _restContext);
    return entry;
  }
  
  @Override
  public WOActionResults updateAction() throws Throwable {
    BlogEntry entry = blogEntryForUniqueTitle();
    update(entry,inFilter());
    editingContext().saveChanges();
    return response(entry, outFilter());
  }
  
  public WOActionResults destroyAction() throws Throwable {
    BlogEntry entry = blogEntryForUniqueTitle();
    editingContext().deleteObject(entry);
    editingContext().saveChanges();
    return response(ERXHttpStatusCodes.OK);
  }
  
  @Override
  public WOActionResults indexAction() throws Throwable {
    ERXRestFetchSpecification<BlogEntry> fetchSpec = new ERXRestFetchSpecification<BlogEntry>(BlogEntry.ENTITY_NAME, null, BlogEntry.LAST_MODIFIED.descs());
    fetchSpec.enableRequestQualifiers(null, outFilter());
    restContext().setUserInfoForKey(new NSTimestampFormatter("%Y-%m-%d"), "er.rest.timestampFormatter");
    return response(fetchSpec,outFilter());
  }
  
  public ERXKeyFilter inFilter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributesAndToOneRelationships();
    filter.include(BlogEntry.CREATED_TIME);
    filter.exclude(BlogEntry.CREATION_TIME);
    filter.include(BlogEntry.CATEGORIES);
    return filter;
  }
  
  public ERXKeyFilter outFilter() {
    return inFilter();
  }
  
  protected ERXRestContext _restContext;

  @Override
  public ERXRestContext restContext() {
    if (_restContext == null) {
      _restContext = super.restContext();
      _restContext.setUserInfoForKey(new NSTimestampFormatter("%Y-%m-%d %H:%M"), "er.rest.timestampFormatter");
    }
    return _restContext;
  }

}
