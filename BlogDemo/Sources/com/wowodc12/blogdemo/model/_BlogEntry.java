// DO NOT EDIT.  Make changes to BlogEntry.java instead.
package com.wowodc12.blogdemo.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _BlogEntry extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "BlogEntry";

  // Attribute Keys
  public static final ERXKey<String> CONTENT = new ERXKey<String>("content");
  public static final ERXKey<NSTimestamp> CREATION_TIME = new ERXKey<NSTimestamp>("creationTime");
  public static final ERXKey<NSTimestamp> LAST_MODIFIED = new ERXKey<NSTimestamp>("lastModified");
  public static final ERXKey<String> TITLE = new ERXKey<String>("title");
  // Relationship Keys
  public static final ERXKey<com.wowodc12.blogdemo.model.Author> AUTHOR = new ERXKey<com.wowodc12.blogdemo.model.Author>("author");
  public static final ERXKey<com.wowodc12.blogdemo.model.Category> CATEGORIES = new ERXKey<com.wowodc12.blogdemo.model.Category>("categories");

  // Attributes
  public static final String CONTENT_KEY = CONTENT.key();
  public static final String CREATION_TIME_KEY = CREATION_TIME.key();
  public static final String LAST_MODIFIED_KEY = LAST_MODIFIED.key();
  public static final String TITLE_KEY = TITLE.key();
  // Relationships
  public static final String AUTHOR_KEY = AUTHOR.key();
  public static final String CATEGORIES_KEY = CATEGORIES.key();

  private static Logger LOG = Logger.getLogger(_BlogEntry.class);

  public BlogEntry localInstanceIn(EOEditingContext editingContext) {
    BlogEntry localInstance = (BlogEntry)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String content() {
    return (String) storedValueForKey(_BlogEntry.CONTENT_KEY);
  }

  public void setContent(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating content from " + content() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.CONTENT_KEY);
  }

  public NSTimestamp creationTime() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.CREATION_TIME_KEY);
  }

  public void setCreationTime(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating creationTime from " + creationTime() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.CREATION_TIME_KEY);
  }

  public NSTimestamp lastModified() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.LAST_MODIFIED_KEY);
  }

  public void setLastModified(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating lastModified from " + lastModified() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.LAST_MODIFIED_KEY);
  }

  public String title() {
    return (String) storedValueForKey(_BlogEntry.TITLE_KEY);
  }

  public void setTitle(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating title from " + title() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.TITLE_KEY);
  }

  public com.wowodc12.blogdemo.model.Author author() {
    return (com.wowodc12.blogdemo.model.Author)storedValueForKey(_BlogEntry.AUTHOR_KEY);
  }
  
  public void setAuthor(com.wowodc12.blogdemo.model.Author value) {
    takeStoredValueForKey(value, _BlogEntry.AUTHOR_KEY);
  }

  public void setAuthorRelationship(com.wowodc12.blogdemo.model.Author value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("updating author from " + author() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setAuthor(value);
    }
    else if (value == null) {
    	com.wowodc12.blogdemo.model.Author oldValue = author();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _BlogEntry.AUTHOR_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _BlogEntry.AUTHOR_KEY);
    }
  }
  
  public NSArray<com.wowodc12.blogdemo.model.Category> categories() {
    return (NSArray<com.wowodc12.blogdemo.model.Category>)storedValueForKey(_BlogEntry.CATEGORIES_KEY);
  }

  public NSArray<com.wowodc12.blogdemo.model.Category> categories(EOQualifier qualifier) {
    return categories(qualifier, null);
  }

  public NSArray<com.wowodc12.blogdemo.model.Category> categories(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc12.blogdemo.model.Category> results;
      results = categories();
      if (qualifier != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.Category>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.Category>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToCategories(com.wowodc12.blogdemo.model.Category object) {
    includeObjectIntoPropertyWithKey(object, _BlogEntry.CATEGORIES_KEY);
  }

  public void removeFromCategories(com.wowodc12.blogdemo.model.Category object) {
    excludeObjectFromPropertyWithKey(object, _BlogEntry.CATEGORIES_KEY);
  }

  public void addToCategoriesRelationship(com.wowodc12.blogdemo.model.Category object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("adding " + object + " to categories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToCategories(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    }
  }

  public void removeFromCategoriesRelationship(com.wowodc12.blogdemo.model.Category object) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("removing " + object + " from categories relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromCategories(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    }
  }

  public com.wowodc12.blogdemo.model.Category createCategoriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc12.blogdemo.model.Category.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _BlogEntry.CATEGORIES_KEY);
    return (com.wowodc12.blogdemo.model.Category) eo;
  }

  public void deleteCategoriesRelationship(com.wowodc12.blogdemo.model.Category object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _BlogEntry.CATEGORIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllCategoriesRelationships() {
    Enumeration<com.wowodc12.blogdemo.model.Category> objects = categories().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteCategoriesRelationship(objects.nextElement());
    }
  }


  public static BlogEntry createBlogEntry(EOEditingContext editingContext, String content
, NSTimestamp creationTime
, NSTimestamp lastModified
, String title
, com.wowodc12.blogdemo.model.Author author) {
    BlogEntry eo = (BlogEntry) EOUtilities.createAndInsertInstance(editingContext, _BlogEntry.ENTITY_NAME);    
		eo.setContent(content);
		eo.setCreationTime(creationTime);
		eo.setLastModified(lastModified);
		eo.setTitle(title);
    eo.setAuthorRelationship(author);
    return eo;
  }

  public static ERXFetchSpecification<BlogEntry> fetchSpec() {
    return new ERXFetchSpecification<BlogEntry>(_BlogEntry.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext) {
    return _BlogEntry.fetchAllBlogEntries(editingContext, null);
  }

  public static NSArray<BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _BlogEntry.fetchBlogEntries(editingContext, null, sortOrderings);
  }

  public static NSArray<BlogEntry> fetchBlogEntries(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<BlogEntry> fetchSpec = new ERXFetchSpecification<BlogEntry>(_BlogEntry.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<BlogEntry> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static BlogEntry fetchBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static BlogEntry fetchBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<BlogEntry> eoObjects = _BlogEntry.fetchBlogEntries(editingContext, qualifier, null);
    BlogEntry eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchRequiredBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    BlogEntry eoObject = _BlogEntry.fetchBlogEntry(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static BlogEntry localInstanceIn(EOEditingContext editingContext, BlogEntry eo) {
    BlogEntry localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
