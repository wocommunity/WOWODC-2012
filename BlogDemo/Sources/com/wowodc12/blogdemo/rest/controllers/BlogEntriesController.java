package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestFetchSpecification;

public class BlogEntriesController extends BaseRestController {

  public BlogEntriesController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
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
    return response(fetchSpec,outFilter());
  }
  
  public ERXKeyFilter inFilter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributesAndToOneRelationships();
    filter.include(BlogEntry.CATEGORIES);
    return filter;
  }
  
  public ERXKeyFilter outFilter() {
    return inFilter();
  }
}
