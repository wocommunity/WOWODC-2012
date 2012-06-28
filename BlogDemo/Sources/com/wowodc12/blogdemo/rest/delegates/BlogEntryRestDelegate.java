package com.wowodc12.blogdemo.rest.delegates;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webobjects.eocontrol.EOClassDescription;
import com.wowodc12.blogdemo.model.BlogEntry;
import com.wowodc12.blogdemo.model.SyncInfo;

import er.extensions.eof.ERXDatabaseContextDelegate;
import er.rest.ERXEORestDelegate;
import er.rest.ERXRestContext;

public class BlogEntryRestDelegate extends ERXEORestDelegate {

  public Object primaryKeyForObject(Object obj, ERXRestContext context) {
    String title = ((BlogEntry)obj).uniqueTitle();
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
    Pattern regexPattern = Pattern.compile("^(.*)\\_([0-9]+)$");
    Matcher regexMatcher = regexPattern.matcher(title);
    if (regexMatcher.matches()) {
      title = regexMatcher.group(1);
    }
    
    BlogEntry blogEntry = BlogEntry.fetchBlogEntry(context.editingContext(), BlogEntry.TITLE.eq(title));
    if (blogEntry == null) {
      throw new ERXDatabaseContextDelegate.ObjectNotAvailableException("Blog entry not found");
    } else {
      return blogEntry;  
    }
  }
    
}
