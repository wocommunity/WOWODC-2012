package com.wowodc12.blogdemo.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.components.ERXComponent;

public class Main extends ERXComponent {
	private final NSDictionary<String,String> _dictSortAuthor = new NSDictionary<String, String>("firstName|desc", "sort");
  private final NSDictionary<String,String> _dictLogin;
  {
    NSMutableDictionary<String,String> md = new NSMutableDictionary<String,String>();
    md.setObjectForKey("probert", "username");
    md.setObjectForKey("wowodc12", "password");
    _dictLogin = md.immutableClone();
  }

  public Main(WOContext context) {
		super(context);
	}

  /**
   * @return the sortAuthor
   */
  public NSDictionary<String, String> dictSortAuthor() {
    return _dictSortAuthor;
  }

  /**
   * @return the dictLogin
   */
  public NSDictionary<String, String> dictLogin() {
    return _dictLogin;
  }
}
