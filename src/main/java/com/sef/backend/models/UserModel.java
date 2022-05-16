package com.sef.backend.models;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

  public final String username;
  public final String password;
  public final boolean isAdmin;
  public final List<RecipeModel> recipes;

  public UserModel(String username, String password, boolean isAdmin) {
    this.username = username;
    this.password = password;
    recipes = new ArrayList<>();
    this.isAdmin = isAdmin;
  }
}
