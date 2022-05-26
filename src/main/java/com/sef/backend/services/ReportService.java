package com.sef.backend.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.PushOptions;
import com.sef.backend.models.RecipeModel;
import com.sef.backend.models.ReportModel;
import com.sef.session.UserSession;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.pushEach;

public class ReportService implements IReportService{
    private final MongoClient mongoClient = MongoClients.create(
            "mongodb+srv://boss:ILoveMongo@maincluster.0aihe.mongodb.net/test"
    );
    private final UserSession userSession = UserSession.getUserSession();

    private MongoCollection<Document> reportsCollection = mongoClient
            .getDatabase("InternationalCuisine")
            .getCollection("Reports");

    @Override
    public int addReport(ReportModel reportModel) {
        try {
            MongoCollection<Document> reports = mongoClient
                    .getDatabase("InternationalCuisine")
                    .getCollection("Reports");
            reports.insertOne(reportToDocument(reportModel));

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static Document reportToDocument(ReportModel reportModel) {
        return new Document()
                .append("_id", reportModel.getReportId())
                .append("recipeId", reportModel.getRecipe())
                .append("reason", reportModel.getReason());
    }
}
