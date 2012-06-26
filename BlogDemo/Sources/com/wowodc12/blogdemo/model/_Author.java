// DO NOT EDIT.  Make changes to Author.java instead.
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
public abstract class _Author extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Author";

  // Attribute Keys
  public static final ERXKey<String> FIRST_NAME = new ERXKey<String>("firstName");
  public static final ERXKey<String> LAST_NAME = new ERXKey<String>("lastName");
  public static final ERXKey<String> PASSWORD = new ERXKey<String>("password");
  public static final ERXKey<String> USERNAME = new ERXKey<String>("username");
  // Relationship Keys
  public static final ERXKey<com.wowodc12.blogdemo.model.BlogEntry> BLOG_ENTRIES = new ERXKey<com.wowodc12.blogdemo.model.BlogEntry>("blogEntries");

  // Attributes
  public static final String FIRST_NAME_KEY = FIRST_NAME.key();
  public static final String LAST_NAME_KEY = LAST_NAME.key();
  public static final String PASSWORD_KEY = PASSWORD.key();
  public static final String USERNAME_KEY = USERNAME.key();
  // Relationships
  public static final String BLOG_ENTRIES_KEY = BLOG_ENTRIES.key();

  private static Logger LOG = Logger.getLogger(_Author.class);

  public Author localInstanceIn(EOEditingContext editingContext) {
    Author localInstance = (Author)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String firstName() {
    return (String) storedValueForKey(_Author.FIRST_NAME_KEY);
  }

  public void setFirstName(String value) {
    if (_Author.LOG.isDebugEnabled()) {
    	_Author.LOG.debug( "updating firstName from " + firstName() + " to " + value);
    }
    takeStoredValueForKey(value, _Author.FIRST_NAME_KEY);
  }

  public String lastName() {
    return (String) storedValueForKey(_Author.LAST_NAME_KEY);
  }

  public void setLastName(String value) {
    if (_Author.LOG.isDebugEnabled()) {
    	_Author.LOG.debug( "updating lastName from " + lastName() + " to " + value);
    }
    takeStoredValueForKey(value, _Author.LAST_NAME_KEY);
  }

  public String password() {
    return (String) storedValueForKey(_Author.PASSWORD_KEY);
  }

  public void setPassword(String value) {
    if (_Author.LOG.isDebugEnabled()) {
    	_Author.LOG.debug( "updating password from " + password() + " to " + value);
    }
    takeStoredValueForKey(value, _Author.PASSWORD_KEY);
  }

  public String username() {
    return (String) storedValueForKey(_Author.USERNAME_KEY);
  }

  public void setUsername(String value) {
    if (_Author.LOG.isDebugEnabled()) {
    	_Author.LOG.debug( "updating username from " + username() + " to " + value);
    }
    takeStoredValueForKey(value, _Author.USERNAME_KEY);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries() {
    return (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)storedValueForKey(_Author.BLOG_ENTRIES_KEY);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries(EOQualifier qualifier) {
    return blogEntries(qualifier, null, false);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries(EOQualifier qualifier, boolean fetch) {
    return blogEntries(qualifier, null, fetch);
  }

  public NSArray<com.wowodc12.blogdemo.model.BlogEntry> blogEntries(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.wowodc12.blogdemo.model.BlogEntry> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.wowodc12.blogdemo.model.BlogEntry.AUTHOR_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.wowodc12.blogdemo.model.BlogEntry.fetchBlogEntries(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = blogEntries();
      if (qualifier != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.wowodc12.blogdemo.model.BlogEntry>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToBlogEntries(com.wowodc12.blogdemo.model.BlogEntry object) {
    includeObjectIntoPropertyWithKey(object, _Author.BLOG_ENTRIES_KEY);
  }

  public void removeFromBlogEntries(com.wowodc12.blogdemo.model.BlogEntry object) {
    excludeObjectFromPropertyWithKey(object, _Author.BLOG_ENTRIES_KEY);
  }

  public void addToBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    if (_Author.LOG.isDebugEnabled()) {
      _Author.LOG.debug("adding " + object + " to blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToBlogEntries(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Author.BLOG_ENTRIES_KEY);
    }
  }

  public void removeFromBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    if (_Author.LOG.isDebugEnabled()) {
      _Author.LOG.debug("removing " + object + " from blogEntries relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromBlogEntries(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Author.BLOG_ENTRIES_KEY);
    }
  }

  public com.wowodc12.blogdemo.model.BlogEntry createBlogEntriesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.wowodc12.blogdemo.model.BlogEntry.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Author.BLOG_ENTRIES_KEY);
    return (com.wowodc12.blogdemo.model.BlogEntry) eo;
  }

  public void deleteBlogEntriesRelationship(com.wowodc12.blogdemo.model.BlogEntry object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Author.BLOG_ENTRIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllBlogEntriesRelationships() {
    Enumeration<com.wowodc12.blogdemo.model.BlogEntry> objects = blogEntries().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteBlogEntriesRelationship(objects.nextElement());
    }
  }


  public static Author createAuthor(EOEditingContext editingContext, String firstName
, String lastName
, String password
, String username
) {
    Author eo = (Author) EOUtilities.createAndInsertInstance(editingContext, _Author.ENTITY_NAME);    
		eo.setFirstName(firstName);
		eo.setLastName(lastName);
		eo.setPassword(password);
		eo.setUsername(username);
    return eo;
  }

  public static ERXFetchSpecification<Author> fetchSpec() {
    return new ERXFetchSpecification<Author>(_Author.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Author> fetchAllAuthors(EOEditingContext editingContext) {
    return _Author.fetchAllAuthors(editingContext, null);
  }

  public static NSArray<Author> fetchAllAuthors(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Author.fetchAuthors(editingContext, null, sortOrderings);
  }

  public static NSArray<Author> fetchAuthors(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Author> fetchSpec = new ERXFetchSpecification<Author>(_Author.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Author> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Author fetchAuthor(EOEditingContext editingContext, String keyName, Object value) {
    return _Author.fetchAuthor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Author fetchAuthor(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Author> eoObjects = _Author.fetchAuthors(editingContext, qualifier, null);
    Author eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Author that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Author fetchRequiredAuthor(EOEditingContext editingContext, String keyName, Object value) {
    return _Author.fetchRequiredAuthor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Author fetchRequiredAuthor(EOEditingContext editingContext, EOQualifier qualifier) {
    Author eoObject = _Author.fetchAuthor(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Author that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Author localInstanceIn(EOEditingContext editingContext, Author eo) {
    Author localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
