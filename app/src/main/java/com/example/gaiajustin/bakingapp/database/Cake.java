package com.example.gaiajustin.bakingapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "cake_table")
public class Cake {

    @PrimaryKey
    private int id;
    private int servings;
    private String name, imageURL;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;


    public Cake(int id, String name, int servings, String imageURL, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    // TODO Should take in a param of cakes, maybe JSON...
    public static ArrayList<Cake> initProductEntryList() {
        // Dummy Data
        String name = "Hello";
        Ingredient ingredients = new Ingredient(19,"Justin", "LOL");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredients);
        Step step = new Step(1,"Press","Press Longer","http:/", "http:/");
        ArrayList<Step> stepList = new ArrayList<>();
        stepList.add(step);

        ArrayList<Cake> cakeList = new ArrayList<>();

        Cake cake = new Cake(1, name, 4, "http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg", ingredientList, stepList);
        cakeList.add(cake);

        Cake secondCake = new Cake(2,name, 2, "http://",ingredientList, stepList);
        cakeList.add(secondCake);

        Cake thirdCake = new Cake(3,"bye", 3, "https://",ingredientList, stepList);
        cakeList.add(thirdCake);
        return cakeList;
    }

}
