package com.sef.backend.controllers;

import com.sef.backend.models.UserModel;
import com.sef.backend.services.UserService;
import java.util.List;

public class UserController {

  private final UserService userService = new UserService();

  public boolean registerUser(String username, String password) {
    return userService.registerUser(username, password);
  }

  public boolean logUserIn(String username, String password) {
    return userService.logUserIn(username, password);
  }

  public List<UserModel> getUsers() {
    return userService.getUsers();
  }

  public UserModel getUser(String username) {
    return userService.getUser(username);
  }

  public boolean deleteUser(String username, String password) {
    return userService.deleteUser(username, password);
  }
}
