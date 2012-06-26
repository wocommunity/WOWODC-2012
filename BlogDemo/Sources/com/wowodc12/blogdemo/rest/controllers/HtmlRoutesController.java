package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.components.BlogEntryIndexPage;

import er.rest.format.ERXRestFormat;

public class HtmlRoutesController extends BaseRestController {

  public HtmlRoutesController(WORequest request) {
    super(request);
  }

  public WOActionResults indexAction() {
    return pageWithName(BlogEntryIndexPage.class);
  }
  
  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.html();
  }
  
}
