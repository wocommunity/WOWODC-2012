package com.wowodc12.blogdemo.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.rest.ERXRestFetchSpecification;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryIndexPage extends ERXComponent implements IERXRouteComponent {
    
  public BlogEntryIndexPage(WOContext context) {
    super(context);
  }
  
  private EOEditingContext _editingContext;
  private BlogEntry _postItem;

  public NSArray<BlogEntry> posts() {
    ERXRestFetchSpecification<BlogEntry> fetchSpec = new ERXRestFetchSpecification<BlogEntry>(BlogEntry.ENTITY_NAME, null, BlogEntry.LAST_MODIFIED.descs());
    return fetchSpec.objects(editingContext(), this.context().request());
  }
  
  public EOEditingContext editingContext() {
    if (_editingContext == null) {
      _editingContext = ERXEC.newEditingContext();
    }
    return _editingContext;
  }
  
  public BlogEntry postItem() {
    return _postItem;
  }
  
  public void setPostItem(BlogEntry postItem) {
    this._postItem = postItem;
  }
  
}