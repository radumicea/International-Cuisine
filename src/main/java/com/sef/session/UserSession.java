package com.sef.session;

import org.bson.types.ObjectId;

public class UserSession {

  private static final UserSession userSession = new UserSession();

  public ObjectId id;

  private UserSession() {}

  public static UserSession getUserSession() {
    return userSession;
  }
}
