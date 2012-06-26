package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.Category;

import er.extensions.eof.ERXKeyFilter;

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
  
}
