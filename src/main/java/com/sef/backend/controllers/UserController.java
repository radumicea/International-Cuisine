package com.sef.backend.controllers;

import com.sef.backend.services.UserService;

public class UserController {

  private final UserService userService = new UserService();

  public int registerUser(String username, String password, boolean isAdmin) {
    return userService.registerUser(username, password, isAdmin);
  }

  public int logUserIn(String username, String password) {
    return userService.logUserIn(username, password);
  }
}
