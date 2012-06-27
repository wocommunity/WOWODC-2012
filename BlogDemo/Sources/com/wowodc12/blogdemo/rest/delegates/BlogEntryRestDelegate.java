package com.wowodc12.blogdemo.rest.delegates;

import java.io.UnsupportedEncodingException;

import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.foundation.NSArray;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.eof.ERXDatabaseContextDelegate;
import er.extensions.eof.ERXQ;
import er.rest.ERXEORestDelegate;
import er.rest.ERXRestContext;

public class BlogEntryRestDelegate extends ERXEORestDelegate {

  public Object primaryKeyForObject(Object obj, ERXRestContext context) {
    String title = ((BlogEntry)obj).title();
    try {
      title = java.net.URLEncoder.encode(title,"UTF-8");
    }
    catch (UnsupportedEncodingException e) {
    }
    return title;
  }

  public Object objectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
    String title = (String)id;
    try {
      title = java.net.URLDecoder.decode(title,"UTF-8");
    }
    catch (UnsupportedEncodingException e) {
    }
    NSArray<BlogEntry> entries = ERXQ.filtered(BlogEntry.fetchAllBlogEntries(context.editingContext()), ERXQ.is("title", title));
    if (entries.size() == 0) {
      throw new ERXDatabaseContextDelegate.ObjectNotAvailableException("Blog entry not found");
    } else {
      return entries.objectAtIndex(0);  
    }
  }
    
}
