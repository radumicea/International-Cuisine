package com.sef.backend.models;

public class RecipeModel {

  private String recipeName;
  private String country;
  private String description;
  private String tags;
  private String image;

  public RecipeModel(
    String recipeName,
    String country,
    String description,
    String tags,
    String image
  ) {
    this.recipeName = recipeName;
    this.description = description;
    this.country = country;
    this.tags = tags;
    this.image = image;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
