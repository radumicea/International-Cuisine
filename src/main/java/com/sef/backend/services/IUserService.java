package com.sef.backend.services;

import com.sef.backend.models.UserModel;
import java.util.List;

public interface IUserService {
  boolean registerUser(String username, String password);
  boolean logUserIn(String username, String password);
  List<UserModel> getUsers();
  UserModel getUser(String username);
  boolean deleteUser(String username, String password);
}
