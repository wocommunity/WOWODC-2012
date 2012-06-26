package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;

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
    return response(ERXHttpStatusCodes.OK);
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
