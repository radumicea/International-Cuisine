package com.sef.backend.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RecipeModel {

  private String name;
  private String country;
  private String description;
  private Set<String> tags;
  private String image;

  public RecipeModel(
    String name,
    String description,
    String country,
    String tags,
    String image
  ) {
    this.name = name;
    this.description = description;
    this.country = country;
    this.tags = new HashSet<>(Arrays.asList(tags.split(",")));
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return String.join(", ", tags);
  }

  public void setTags(String tags) {
    this.tags = new HashSet<>(Arrays.asList(tags.split(", ")));
  }

  public boolean addTags(String tag) {
    return tags.add(tag);
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
