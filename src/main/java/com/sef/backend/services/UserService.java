package com.sef.backend.services;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.sef.backend.managers.HashManager;
import com.sef.backend.models.RecipeModel;
import com.sef.session.UserSession;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class UserService implements IUserService {

  private final MongoClient mongoClient;
  private final HashManager hashManager;
  private final UserSession userSession = UserSession.getUserSession();

  public UserService() {
    mongoClient =
      MongoClients.create(
        "mongodb+srv://boss:ILoveMongo@maincluster.0aihe.mongodb.net/test"
      );
    hashManager = new HashManager();
  }

  @Override
  public int registerUser(String username, String password) {
    try {
      MongoCollection<Document> users = mongoClient
        .getDatabase("InternationalCuisine")
          .getCollection("Users");
        
      users.createIndex(Indexes.ascending("username"));

      Bson projectionFields = Projections.fields(
        Projections.include("username"),
        Projections.excludeId()
      );

      Document user = users
        .find(eq("username", username))
        .projection(projectionFields)
        .first();

      if (user != null) {
        return 1;
      }

      users.insertOne(
        new Document()
          .append("_id", new ObjectId())
          .append("username", username)
          .append("password", hashManager.hashPassword(password))
          .append("isAdmin", false)
          .append("recipes", new ArrayList<RecipeModel>())
      );

      return 0;
    } catch (Exception e) {
      return -1;
    }
  }

  @Override
  public int logUserIn(String username, String password) {
    try {
      MongoCollection<Document> users = mongoClient
        .getDatabase("InternationalCuisine")
        .getCollection("Users");

      Bson projectionFields = Projections.fields(
        Projections.include("username", "password")
      );

      Document user = users
        .find(eq("username", username))
        .projection(projectionFields)
        .first();

      if (user == null) {
        return 1;
      }

      String hashedPassword = user.get("password", String.class);

      if (hashManager.checkPassword(hashedPassword, password)) {
        userSession.userId = user.get("_id", ObjectId.class);
        return 0;
      } else {
        return 1;
      }
    } catch (Exception e) {
      return -1;
    }
  }
}
