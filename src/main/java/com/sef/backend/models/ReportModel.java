package com.sef.backend.models;

import org.bson.types.ObjectId;

public class ReportModel {
    private ObjectId _id;
    private ObjectId recipe;
    private String reason;

    public ReportModel(RecipeModel recipe, String reason)
    {
        this._id = new ObjectId();
        this.recipe = recipe.getRecipeId();
        this.reason = reason;
    }

    public ObjectId getReportId(){
        return this._id;
    }
    public ObjectId getRecipe() {
        return this.recipe;
    }

    public String getReason(){
        return this.reason;
    }
}
