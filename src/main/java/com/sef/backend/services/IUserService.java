package com.sef.backend.services;

public interface IUserService {
  int registerUser(String username, String password, boolean isAdmin);
  int logUserIn(String username, String password);
}
