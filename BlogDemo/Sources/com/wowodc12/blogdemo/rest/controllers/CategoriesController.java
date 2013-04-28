package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.Category;

import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestFetchSpecification;

public class CategoriesController extends BaseRestController {

  public CategoriesController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    Category newCategory = create(ERXKeyFilter.filterWithAttributes());
    editingContext().saveChanges();
    return response(newCategory, ERXKeyFilter.filterWithAttributes());
  }
  
  @Override
  public WOActionResults indexAction() throws Throwable {
    ERXRestFetchSpecification<Category> fetchSpec = new ERXRestFetchSpecification<Category>(Category.ENTITY_NAME, null, Category.NAME.ascs());
    fetchSpec.enableRequestQualifiers(null, ERXKeyFilter.filterWithAttributes());
    return response(fetchSpec,ERXKeyFilter.filterWithAttributes());
  }

  @Override
  public WOActionResults showAction() throws Throwable {
    Category category = routeObjectForKey("category");
    return response(category,ERXKeyFilter.filterWithAttributes());
  }
  
}
