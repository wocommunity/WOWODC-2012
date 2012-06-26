package com.wowodc12.blogdemo.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXDefaultRouteController;

public class BaseRestController extends ERXDefaultRouteController {

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

}
