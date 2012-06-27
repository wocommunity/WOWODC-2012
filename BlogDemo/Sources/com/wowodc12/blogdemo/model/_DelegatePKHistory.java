// DO NOT EDIT.  Make changes to DelegatePKHistory.java instead.
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
public abstract class _DelegatePKHistory extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "DelegatePKHistory";

  // Attribute Keys
  public static final ERXKey<String> DELEGATED_PRIMARY_KEY_VALUE = new ERXKey<String>("delegatedPrimaryKeyValue");
  // Relationship Keys
  public static final ERXKey<com.wowodc12.blogdemo.model.SyncInfo> SYNC_INFO = new ERXKey<com.wowodc12.blogdemo.model.SyncInfo>("syncInfo");

  // Attributes
  public static final String DELEGATED_PRIMARY_KEY_VALUE_KEY = DELEGATED_PRIMARY_KEY_VALUE.key();
  // Relationships
  public static final String SYNC_INFO_KEY = SYNC_INFO.key();

  private static Logger LOG = Logger.getLogger(_DelegatePKHistory.class);

  public DelegatePKHistory localInstanceIn(EOEditingContext editingContext) {
    DelegatePKHistory localInstance = (DelegatePKHistory)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String delegatedPrimaryKeyValue() {
    return (String) storedValueForKey(_DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public void setDelegatedPrimaryKeyValue(String value) {
    if (_DelegatePKHistory.LOG.isDebugEnabled()) {
    	_DelegatePKHistory.LOG.debug( "updating delegatedPrimaryKeyValue from " + delegatedPrimaryKeyValue() + " to " + value);
    }
    takeStoredValueForKey(value, _DelegatePKHistory.DELEGATED_PRIMARY_KEY_VALUE_KEY);
  }

  public com.wowodc12.blogdemo.model.SyncInfo syncInfo() {
    return (com.wowodc12.blogdemo.model.SyncInfo)storedValueForKey(_DelegatePKHistory.SYNC_INFO_KEY);
  }
  
  public void setSyncInfo(com.wowodc12.blogdemo.model.SyncInfo value) {
    takeStoredValueForKey(value, _DelegatePKHistory.SYNC_INFO_KEY);
  }

  public void setSyncInfoRelationship(com.wowodc12.blogdemo.model.SyncInfo value) {
    if (_DelegatePKHistory.LOG.isDebugEnabled()) {
      _DelegatePKHistory.LOG.debug("updating syncInfo from " + syncInfo() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setSyncInfo(value);
    }
    else if (value == null) {
    	com.wowodc12.blogdemo.model.SyncInfo oldValue = syncInfo();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _DelegatePKHistory.SYNC_INFO_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _DelegatePKHistory.SYNC_INFO_KEY);
    }
  }
  

  public static DelegatePKHistory createDelegatePKHistory(EOEditingContext editingContext, String delegatedPrimaryKeyValue
, com.wowodc12.blogdemo.model.SyncInfo syncInfo) {
    DelegatePKHistory eo = (DelegatePKHistory) EOUtilities.createAndInsertInstance(editingContext, _DelegatePKHistory.ENTITY_NAME);    
		eo.setDelegatedPrimaryKeyValue(delegatedPrimaryKeyValue);
    eo.setSyncInfoRelationship(syncInfo);
    return eo;
  }

  public static ERXFetchSpecification<DelegatePKHistory> fetchSpec() {
    return new ERXFetchSpecification<DelegatePKHistory>(_DelegatePKHistory.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<DelegatePKHistory> fetchAllDelegatePKHistories(EOEditingContext editingContext) {
    return _DelegatePKHistory.fetchAllDelegatePKHistories(editingContext, null);
  }

  public static NSArray<DelegatePKHistory> fetchAllDelegatePKHistories(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _DelegatePKHistory.fetchDelegatePKHistories(editingContext, null, sortOrderings);
  }

  public static NSArray<DelegatePKHistory> fetchDelegatePKHistories(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<DelegatePKHistory> fetchSpec = new ERXFetchSpecification<DelegatePKHistory>(_DelegatePKHistory.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<DelegatePKHistory> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static DelegatePKHistory fetchDelegatePKHistory(EOEditingContext editingContext, String keyName, Object value) {
    return _DelegatePKHistory.fetchDelegatePKHistory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static DelegatePKHistory fetchDelegatePKHistory(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<DelegatePKHistory> eoObjects = _DelegatePKHistory.fetchDelegatePKHistories(editingContext, qualifier, null);
    DelegatePKHistory eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one DelegatePKHistory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static DelegatePKHistory fetchRequiredDelegatePKHistory(EOEditingContext editingContext, String keyName, Object value) {
    return _DelegatePKHistory.fetchRequiredDelegatePKHistory(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static DelegatePKHistory fetchRequiredDelegatePKHistory(EOEditingContext editingContext, EOQualifier qualifier) {
    DelegatePKHistory eoObject = _DelegatePKHistory.fetchDelegatePKHistory(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no DelegatePKHistory that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static DelegatePKHistory localInstanceIn(EOEditingContext editingContext, DelegatePKHistory eo) {
    DelegatePKHistory localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
