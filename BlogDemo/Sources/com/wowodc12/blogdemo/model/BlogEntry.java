package com.wowodc12.blogdemo.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.eof.ERXKey;

public class BlogEntry extends _BlogEntry {
  @SuppressWarnings("unused")
  private static Logger log = Logger.getLogger(BlogEntry.class);

  @Override
  public void awakeFromInsertion(EOEditingContext editingContext) {
    super.awakeFromInsertion(editingContext);
    this.setCreationTime(new NSTimestamp());
    this.setLastModified(new NSTimestamp());
  }

  @Override
  public void willUpdate() {
    super.willUpdate();
    this.setLastModified(new NSTimestamp());
  }
  
  public static final ERXKey<NSTimestamp> CREATED_TIME = new ERXKey<NSTimestamp>("createdTime");
  
  public NSTimestamp createdTime() {
    return super.creationTime();
  }
  
  public void setCreatedTime(NSTimestamp createdTime) {
    super.setCreationTime(createdTime);
  }
  
  public String uniqueTitle() {
    return this.title() + "_" + this.primaryKey();
  }
}
