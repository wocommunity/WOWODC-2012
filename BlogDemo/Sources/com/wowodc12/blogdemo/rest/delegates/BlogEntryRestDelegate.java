package com.wowodc12.blogdemo.rest.delegates;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOEntityClassDescription;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGlobalID;
import com.wowodc12.blogdemo.model.BlogEntry;

import er.extensions.eof.ERXDatabaseContextDelegate;
import er.extensions.eof.ERXEOControlUtilities;
import er.rest.ERXEORestDelegate;
import er.rest.ERXRestClassDescriptionFactory;
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
    return blogEntryWithId(id, context.editingContext());
  }
    
  /*
   * The same method can be called from this delegate for non-html routes and BlogEntryShowPage for html routes
   */
  public static BlogEntry blogEntryWithId(Object id, EOEditingContext ec) {
    String title = (String)id;
    try {
      title = java.net.URLDecoder.decode(title,"UTF-8");
    }
    catch (UnsupportedEncodingException e) {
    }
    BlogEntry blogEntry;
    //We have defined a unique title for each blog so need to only find blogs which match unique title even if title not unique
    Pattern regexPattern = Pattern.compile("^(.*)\\_([0-9]+)$");
    Matcher regexMatcher = regexPattern.matcher(title);
    if (regexMatcher.matches()) {
      title = regexMatcher.group(1);
      String strPkValue = regexMatcher.group(2);
      blogEntry = blogEntryWithEofId(strPkValue, ec);
      if ( blogEntry.title() == null ) {
        if ( title != null ) blogEntry = null;
      } else {
        if ( !blogEntry.title().equals(title) ) blogEntry = null;
      }
    } else {
      //timc: I am not sure if auto locking works in REST but I noticed super class was locking, so I have added locking here
      ec.lock();
      try {
        blogEntry = BlogEntry.fetchBlogEntry(ec, BlogEntry.TITLE.eq(title));
      } finally {
        ec.unlock();
      }
    }
    
    if (blogEntry == null) {
      throw new ERXDatabaseContextDelegate.ObjectNotAvailableException("Blog entry not found");
    } else {
      return blogEntry;  
    }
  }
  
  /*
   * Returns the blog entry when the EOF primary key is provided as a String (which will be parsed to an Integer)
   * Copied from ERXEOFRestDelegate objectOfEntityWithID
   */
  public static BlogEntry blogEntryWithEofId(String strPkValue, EOEditingContext editingContext ) {
    EOEntity eoEntity = ((EOEntityClassDescription) ERXRestClassDescriptionFactory.classDescriptionForClass(BlogEntry.class,false)).entity();
    eoEntity.primaryKeyAttributes().objectAtIndex(0).validateValue(strPkValue);
    if (editingContext == null) {
      throw new IllegalArgumentException("There was no editing context while trying to find blogEntryWithEofId.");
    }
    editingContext.lock();
    try {
      EOGlobalID gid = ERXEOControlUtilities.globalIDForString(editingContext, BlogEntry.ENTITY_NAME, strPkValue);
      return (BlogEntry) editingContext.faultForGlobalID(gid, editingContext);
    }
    finally {
      editingContext.unlock();
    }
  }
    
}
