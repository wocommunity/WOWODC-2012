package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOCookie;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.wowodc12.blogdemo.model.Author;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.crypting.ERXBlowfishCrypter;
import er.extensions.eof.ERXKeyFilter;
import er.rest.ERXRestFetchSpecification;

public class AuthorsController extends BaseRestController {

  public static final ERXBlowfishCrypter crypter = new ERXBlowfishCrypter();
  
  public AuthorsController(WORequest request) {
    super(request);
  }

  public WOActionResults createAction() throws Throwable {
    Author newAuthor = create(createInFilter());
    editingContext().saveChanges();
    return response(newAuthor, outFilter());
  }
  
  public WOActionResults loginAction() throws Throwable {
    String username = request().stringFormValueForKey("username");
    String password = request().stringFormValueForKey("password");
    Author author = Author.fetchAuthor(editingContext(), Author.USERNAME.eq(username).and(Author.PASSWORD.eq(password)));
    if (author != null) {
      //We need to set the cookie path to /cgi-bin/WebObjects/BlogDemo.woa
      //Otherwise, if we set it null, it will default to /cgi-bin/WebObjects/BlogDemo.woa/ra/authors
      //which means we can't use it easily with other requests.
      //We are using the same path which WOSession uses by default when it uses cookies to track sessions
      String path = request() == null ? null : request().adaptorPrefix()+"/"+request().applicationName()+".woa" ;
      WOResponse response = new WOResponse();
      response.addCookie(new WOCookie(COOKIE_NAME, crypter.encrypt(username),path,null,-1,false));
      return response;
    } else {
      return response(ERXHttpStatusCodes.UNAUTHORIZED);
    }
  }
  
  public ERXKeyFilter createInFilter() {
    return ERXKeyFilter.filterWithAttributes();
  }
  
  public ERXKeyFilter outFilter() {
    ERXKeyFilter out = createInFilter();
    out.exclude(Author.PASSWORD);
    return out;
  }

  @Override
  public WOActionResults destroyAction() throws Throwable {
    Author author = routeObjectForKey("author");
    editingContext().deleteObject(author);
    editingContext().saveChanges();
    return response(ERXHttpStatusCodes.OK);
  }
  
  @Override
  public WOActionResults indexAction() throws Throwable {
    ERXRestFetchSpecification<Author> fetchSpec = new ERXRestFetchSpecification<Author>(Author.ENTITY_NAME, null, Author.LAST_NAME.asc().then(Author.FIRST_NAME.asc()));
    fetchSpec.enableRequestQualifiers(null, outFilter());
    return response(fetchSpec,outFilter());
  }

  @Override
  public WOActionResults showAction() throws Throwable {
    Author author = routeObjectForKey("author");
    return response(author,outFilter());
  }
  
  @Override
  public WOActionResults updateAction() throws Throwable {
    Author author = routeObjectForKey("author");
    update(author,createInFilter());
    editingContext().saveChanges();
    return response(author,outFilter());
  }
  
  
}
