package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSTimestampFormatter;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestContext;
import er.rest.ERXRestFetchSpecification;

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
    BlogEntry entry = routeObjectForKey("blogEntry");
    return response(entry, outFilter());
  }
  
  public WOActionResults destroyAction() throws Throwable {
    BlogEntry entry = routeObjectForKey("blogEntry");
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
