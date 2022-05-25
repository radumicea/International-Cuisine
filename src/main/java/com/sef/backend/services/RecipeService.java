package com.sef.backend.services;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.sef.backend.models.RecipeModel;
import com.sef.session.UserSession;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class RecipeService implements IRecipeService {

  private final MongoClient mongoClient = MongoClients.create(
    "mongodb+srv://boss:ILoveMongo@maincluster.0aihe.mongodb.net/test"
  );
  private final UserSession userSession = UserSession.getUserSession();

  private MongoCollection<Document> cachedUsers = mongoClient
    .getDatabase("InternationalCuisine")
    .getCollection("Users");

  private MongoCollection<Document> cachedRecipes = mongoClient
    .getDatabase("InternationalCuisine")
    .getCollection("Recipes");

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
        addToSet("recipes", recipeToDocument(recipe))
      );

      recipes.insertOne(recipeToDocument(recipe));

      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int updateRecipe(RecipeModel recipe) {
    try {
      MongoCollection<Document> users = mongoClient
        .getDatabase("InternationalCuisine")
        .getCollection("Users");

      MongoCollection<Document> recipes = mongoClient
        .getDatabase("InternationalCuisine")
        .getCollection("Recipes");

      users.updateOne(
        eq("_id", userSession.userId),
        addToSet("recipes", recipeToDocument(recipe))
      );

      recipes.updateOne(
        eq("_id", recipe.getRecipeId()),
        combine(
          set("recipeName", recipe.getRecipeName()),
          set("country", recipe.getCountry()),
          set("description", recipe.getDescription()),
          set("tags", recipe.getTags()),
          set("image", recipe.getImage())
        )
      );

      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public void refresh() {
    cachedUsers =
      mongoClient.getDatabase("InternationalCuisine").getCollection("Users");

    cachedRecipes =
      mongoClient.getDatabase("InternationalCuisine").getCollection("Recipes");
  }

  @Override
  public List<RecipeModel> getFromToRecipes(int from, int to) {
    try {
      List<RecipeModel> recipeList = new ArrayList<>();

      FindIterable<Document> docs = cachedRecipes
        .find()
        .sort(new BasicDBObject("_id", -1))
        .skip(from)
        .limit(to);

      for (Document doc : docs) {
        recipeList.add(documentToRecipe(doc));
      }

      return recipeList;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<RecipeModel> getUserFromToRecipes(
    ObjectId userId,
    int from,
    int to
  ) {
    try {
      Bson projectionFields = Projections.fields(
        Projections.include("recipes")
      );

      Document user = cachedUsers
        .find(eq("_id", userId))
        .projection(projectionFields)
        .first();

      if (user == null) {
        return null;
      }

      List<Document> documents = user.get("recipes", List.class);
      if (from >= documents.size()) {
        return null;
      }
      documents = documents.subList(from, Math.min(to, documents.size()));

      List<RecipeModel> recipes = new ArrayList<>();
      for (Document doc : documents) {
        recipes.add(documentToRecipe(doc));
      }

      return recipes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Document recipeToDocument(RecipeModel recipe) {
    return new Document()
      .append("_id", recipe.getRecipeId())
      .append("recipeName", recipe.getRecipeName())
      .append("country", recipe.getCountry())
      .append("description", recipe.getDescription())
      .append("tags", recipe.getTags())
      .append("image", recipe.getImage());
  }

  private static RecipeModel documentToRecipe(Document document) {
    return new RecipeModel(
      document.get("_id", ObjectId.class),
      document.get("recipeName", String.class),
      document.get("country", String.class),
      document.get("description", String.class),
      document.get("tags", String.class),
      document.get("image", String.class)
    );
  }
}
