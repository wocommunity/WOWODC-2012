package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.Author;

import er.extensions.eof.ERXKeyFilter;

public class AuthorsController extends BaseRestController {

  public AuthorsController(WORequest request) {
    super(request);
  }

  public WOActionResults createAction() throws Throwable {
    Author newAuthor = create(createInFilter());
    editingContext().saveChanges();
    return response(newAuthor, outFilter());
  }
  
  public ERXKeyFilter createInFilter() {
    return ERXKeyFilter.filterWithAttributes();
  }
  
  public ERXKeyFilter outFilter() {
    ERXKeyFilter out = createInFilter();
    out.exclude(Author.PASSWORD);
    return out;
  }
}
