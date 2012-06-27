package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOCookie;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.wowodc12.blogdemo.model.Author;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.crypting.ERXBlowfishCrypter;
import er.extensions.eof.ERXKeyFilter;

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
      WOResponse response = new WOResponse();
      response.addCookie(new WOCookie(COOKIE_NAME, crypter.encrypt(username)));
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
}
