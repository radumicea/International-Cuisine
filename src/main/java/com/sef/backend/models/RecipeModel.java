package com.sef.backend.models;

import org.bson.types.ObjectId;

public class RecipeModel {

  private ObjectId recipeId;
  private String recipeName;
  private String country;
  private String description;
  private String tags;
  private String image;

  public RecipeModel(
    ObjectId recipeId,
    String recipeName,
    String country,
    String description,
    String tags,
    String image
  ) {
    this.recipeId = recipeId;
    this.recipeName = recipeName;
    this.description = description;
    this.country = country;
    this.tags = tags;
    this.image = image;
  }

  public ObjectId getRecipeId() {
    return recipeId;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((recipeId == null) ? 0 : recipeId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RecipeModel other = (RecipeModel) obj;
    if (recipeId == null) {
      if (other.recipeId != null)
        return false;
    } else if (!recipeId.equals(other.recipeId))
      return false;
    return true;
  }
}
