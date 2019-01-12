package com.example.gaiajustin.bakingapp.database;

public class Ingredient {
    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Ingredient(int quantity, String measure, String ingredient) {
        this.quantity = Double.valueOf(quantity);
        this.measure = measure;
        this.ingredient = ingredient;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Double.valueOf(quantity);
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
