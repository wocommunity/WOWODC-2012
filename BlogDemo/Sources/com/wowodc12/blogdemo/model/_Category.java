// DO NOT EDIT.  Make changes to Category.java instead.
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
public abstract class _Category extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Category";

  // Attribute Keys
  public static final ERXKey<String> NAME = new ERXKey<String>("name");
  // Relationship Keys
  public static final ERXKey<com.wowodc12.blogdemo.model.BlogEntry> BLOG_ENTRIES = new ERXKey<com.wowodc12.blogdemo.model.BlogEntry>("blogEntries");

  // Attributes
  public static final String NAME_KEY = NAME.key();
  // Relationships
  public static final String BLOG_ENTRIES_KEY = BLOG_ENTRIES.key();

  private static Logger LOG = Logger.getLogger(_Category.class);

  public Category localInstanceIn(EOEditingContext editingContext) {
    Category localInstance = (Category)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String name() {
    return (String) storedValueForKey(_Category.NAME_KEY);
  }

  public void setName(String value) {
    if (_Category.LOG.isDebugEnabled()) {
    	_Category.LOG.debug( "updating name from " + name() + " to " + value);
    }
    takeStoredValueForKey(value, _Category.NAME_KEY);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries() {
    return (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)storedValueForKey(_Category.BLOG_ENTRIES_KEY);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries(EOQualifier qualifier) {
    return blogEntries(qualifier, null);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.wowodc12.blogdemo.model.BlogEntry> results;
      results = blogEntries();
      if (qualifier != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToBlogEntries(com.wowodc12.blogdemo.model.BlogEntry object) {
    includeObjectIntoPropertyWithKey(object, _Category.BLOG_ENTRIES_KEY);
  }

  public void removeFromBlogEntries(com.wowodc12.blogdemo.model.BlogEntry object) {
    excludeObjectFromPropertyWithKey(object, _Category.BLOG_ENTRIES_KEY);
  }

  public void addToBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    if (_Category.LOG.isDebugEnabled()) {
      _Category.LOG.debug("adding " + object + " to blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToBlogEntries(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Category.BLOG_ENTRIES_KEY);
    }
  }

  public void removeFromBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    if (_Category.LOG.isDebugEnabled()) {
      _Category.LOG.debug("removing " + object + " from blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromBlogEntries(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Category.BLOG_ENTRIES_KEY);
    }
  }

  public com.wowodc12.blogdemo.model.BlogEntry createBlogEntriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc12.blogdemo.model.BlogEntry.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Category.BLOG_ENTRIES_KEY);
    return (com.wowodc12.blogdemo.model.BlogEntry) eo;
  }

  public void deleteBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Category.BLOG_ENTRIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllBlogEntriesRelationships() {
    Enumeration<com.wowodc12.blogdemo.model.BlogEntry> objects = blogEntries().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteBlogEntriesRelationship(objects.nextElement());
    }
  }


  public static Category createCategory(EOEditingContext editingContext, String name
) {
    Category eo = (Category) EOUtilities.createAndInsertInstance(editingContext, _Category.ENTITY_NAME);    
		eo.setName(name);
    return eo;
  }

  public static ERXFetchSpecification<Category> fetchSpec() {
    return new ERXFetchSpecification<Category>(_Category.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Category> fetchAllCategories(EOEditingContext editingContext) {
    return _Category.fetchAllCategories(editingContext, null);
  }

  public static NSArray<Category> fetchAllCategories(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Category.fetchCategories(editingContext, null, sortOrderings);
  }

  public static NSArray<Category> fetchCategories(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Category> fetchSpec = new ERXFetchSpecification<Category>(_Category.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Category> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Category fetchCategory(EOEditingContext editingContext, String keyName, Object value) {
    return _Category.fetchCategory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Category fetchCategory(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Category> eoObjects = _Category.fetchCategories(editingContext, qualifier, null);
    Category eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Category that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Category fetchRequiredCategory(EOEditingContext editingContext, String keyName, Object value) {
    return _Category.fetchRequiredCategory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Category fetchRequiredCategory(EOEditingContext editingContext, EOQualifier qualifier) {
    Category eoObject = _Category.fetchCategory(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Category that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Category localInstanceIn(EOEditingContext editingContext, Category eo) {
    Category localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
