package com.sef.backend.managers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashManager implements IHashManager {

  private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public String hashPassword(String password) {
    return encoder.encode(password);
  }

  @Override
  public boolean checkPassword(String hashedPassword, String password) {
    return encoder.matches(password, hashedPassword);
  }
}
