package com.sef.backend.managers;

public interface IHashManager {
  String hashPassword(String password) throws Exception;
  boolean checkPassword(String hash, String password) throws Exception;
}
