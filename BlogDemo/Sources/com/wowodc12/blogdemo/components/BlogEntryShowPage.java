package com.wowodc12.blogdemo.components;

import com.webobjects.appserver.WOContext;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.components.ERXComponent;
import er.rest.routes.ERXRouteParameter;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryShowPage extends ERXComponent implements IERXRouteComponent {
  
  public BlogEntryShowPage(WOContext context) {
    super(context);
  }
  
  private BlogEntry _blogEntry;

  public BlogEntry blogEntry() {
    return _blogEntry;
  }
  
  @ERXRouteParameter
  public void setBlogEntry(BlogEntry blogEntry) {
    this._blogEntry = blogEntry;
  }
  
}