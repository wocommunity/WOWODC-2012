package com.wowodc12.blogdemo.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.wowodc12.blogdemo.model.BlogEntry;
import com.wowodc12.blogdemo.rest.delegates.BlogEntryRestDelegate;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.rest.routes.ERXRouteParameter;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryShowPage extends ERXComponent implements IERXRouteComponent {
  
  public BlogEntryShowPage(WOContext context) {
    super(context);
  }
  
  private BlogEntry _blogEntry;
  private EOEditingContext _editingContext = ERXEC.newEditingContext();

  public BlogEntry blogEntry() {
    return _blogEntry;
  }
  
  public void setBlogEntry(BlogEntry blogEntry) {
    this._blogEntry = blogEntry;
  }
  
  @ERXRouteParameter
  public void setUniqueTitle(String uniqueTitle) {
    BlogEntry entry = BlogEntryRestDelegate.blogEntryWithId(uniqueTitle, _editingContext); 
    setBlogEntry(entry);
  }
  
}