package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.wowodc12.blogdemo.model.Author;

import er.extensions.crypting.ERXBlowfishCrypter;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXDefaultRouteController;

public class BaseRestController extends ERXDefaultRouteController {

  public static final ERXBlowfishCrypter crypter = new ERXBlowfishCrypter();
  public static final String COOKIE_NAME = "BlogToken";
  
  public BaseRestController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults newAction() throws Throwable {
    return null;
  }

  @Override
  public WOActionResults updateAction() throws Throwable {
    return null;
  }

  @Override
  public WOActionResults destroyAction() throws Throwable {
    return null;
  }

  @Override
  public WOActionResults showAction() throws Throwable {
    return null;
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    return null;
  }

  @Override
  public WOActionResults indexAction() throws Throwable {
    return null;
  }
  
  @Override
  protected ERXRestFormat defaultFormat() {
    return ERXRestFormat.json();
  }
  
  @Override
  protected boolean isAutomaticHtmlRoutingEnabled() {
    return true;
  }
  
  public Author checkSecurity() {
    String cookieValue = this.request().cookieValueForKey(COOKIE_NAME);
    if (cookieValue != null) {
      String decryptedValue = crypter.decrypt(cookieValue);
      Author loggedUser = Author.fetchAuthor(editingContext(), Author.USERNAME_KEY, decryptedValue);
      if (loggedUser != null) {
        return loggedUser;
      }
    }
    throw new SecurityException();
  }

}
