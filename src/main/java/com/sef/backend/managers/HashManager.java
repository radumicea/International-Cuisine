package com.sef.backend.managers;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashManager implements IHashManager {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int SALT_SIZE = 16; // 128 bit
  private static final int KEY_LENGTH = 32; // 256 bit
  private static final int INTERATION_COUNT = 1000;

  @Override
  public String hashPassword(String password) throws Exception {
    byte[] salt = new byte[SALT_SIZE];
    RANDOM.nextBytes(salt);

    KeySpec spec = new PBEKeySpec(
      password.toCharArray(),
      salt,
      INTERATION_COUNT,
      KEY_LENGTH
    );

    SecretKeyFactory factory = SecretKeyFactory.getInstance(
      "PBKDF2WithHmacSHA1"
    );

    return new String(factory.generateSecret(spec).getEncoded());
  }

  @Override
  public boolean checkPassword(String hash, String password) throws Exception {
    return hash.equals(hashPassword(password));
  }
}
