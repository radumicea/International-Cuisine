package com.sef.backend.services;

import com.sef.backend.models.UserModel;
import java.util.List;

public class UserService implements IUserService {

  @Override
  public boolean registerUser(String username, String password) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean logUserIn(String username, String password) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<UserModel> getUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserModel getUser(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteUser(String username, String password) {
    // TODO Auto-generated method stub
    return false;
  }
}
