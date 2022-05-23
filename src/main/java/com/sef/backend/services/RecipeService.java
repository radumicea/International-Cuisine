package com.sef.backend.services;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.sef.backend.models.RecipeModel;
import com.sef.session.UserSession;
import org.bson.Document;
import org.bson.types.ObjectId;

public class RecipeService implements IRecipeService {

  private final MongoClient mongoClient;
  private final UserSession userSession = UserSession.getUserSession();

  public RecipeService() {
    mongoClient =
      MongoClients.create(
        "mongodb+srv://boss:ILoveMongo@maincluster.0aihe.mongodb.net/test"
      );
  }

  @Override
  public int addRecipe(RecipeModel recipe) {
    try {
      MongoCollection<Document> users = mongoClient
        .getDatabase("InternationalCuisine")
        .getCollection("Users");

      MongoCollection<Document> recipes = mongoClient
        .getDatabase("InternationalCuisine")
        .getCollection("Recipes");

      users.updateOne(
        eq("_id", userSession.userId),
        Updates.addToSet("recipes", recipeToDocument(recipe))
      );

      recipes.insertOne(recipeToDocument(recipe));

      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  private static Document recipeToDocument(RecipeModel recipe) {
    return new Document()
      .append("_id", new ObjectId())
      .append("name", recipe.getName())
      .append("description", recipe.getDescription())
      .append("country", recipe.getCountry())
      .append("tags", recipe.getTags())
      .append("image", recipe.getImage());
  }
}
