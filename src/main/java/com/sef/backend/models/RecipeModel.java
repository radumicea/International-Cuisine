package com.sef.backend.models;

import java.util.List;

public class RecipeModel {

  public String name;
  public String description;
  public Country country;
  public List<String> types;

  public RecipeModel(
    String name,
    String description,
    Country country,
    List<String> types
  ) {
    this.name = name;
    this.description = description;
    this.country = country;
    this.types = types;
  }
}
