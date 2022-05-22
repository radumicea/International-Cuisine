package com.sef.backend.managers;

public interface IHashManager {
  String hashPassword(String password);

  boolean checkPassword(String hashedPassword, String password);
}
