package com.example.gaiajustin.bakingapp.database;

import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class Cake {

    private int id, servings;
    private String name, imageURL;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    public Cake(int id, String name, int servings, String imageURL, List<Ingredient> ingredients, List<Step> steps) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    // TODO Should take in a param of cakes, maybe JSON...
    public static List<Cake> initProductEntryList() {
        // Dummy Data
        int id = 1;
        String name = "Hello";
        Ingredient ingredients = new Ingredient(19,"Justin", "LOL");
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredients);
        Step step = new Step(1,"Press","Press Longer","http:/", "http:/");
        List<Step> stepList = new ArrayList<>();
        stepList.add(step);

        List<Cake> cakeList = new ArrayList<>();

        Cake cake = new Cake(id, name, 4, "http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg", ingredientList, stepList);
        cakeList.add(cake);

        Cake secondCake = new Cake(id,name, 2, "http://",ingredientList, stepList);
        cakeList.add(secondCake);

        Cake thirdCake = new Cake(id,"bye", 3, "https://",ingredientList, stepList);
        cakeList.add(thirdCake);
        cakeList.add(cake);
        cakeList.add(cake);
        cakeList.add(cake);
        cakeList.add(cake);

        return cakeList;
    }

}
