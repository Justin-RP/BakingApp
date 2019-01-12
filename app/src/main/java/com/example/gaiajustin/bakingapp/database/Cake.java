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

        // Dummy URL
        // http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg

        ArrayList<Cake> cakeList = new ArrayList<>();

        // CAKE 1

        Ingredient cake1ingredient1 = new Ingredient(2,"CUP", "Graham Cracker crumbs");
        Ingredient cake1ingredient2 = new Ingredient(6,"TBLSP", "unsalted butter, melted");
        Ingredient cake1ingredient3 = new Ingredient(0.5,"CUP", "granulated sugar");
        Ingredient cake1ingredient4 = new Ingredient(1.5,"TSP", "salt");

        ArrayList<Ingredient> ingredientList1 = new ArrayList<>();

        ingredientList1.add(cake1ingredient1);
        ingredientList1.add(cake1ingredient2);
        ingredientList1.add(cake1ingredient3);
        ingredientList1.add(cake1ingredient4);


        Step cake1step1 = new Step(0,"Recipe Introduction","Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
                "");
        Step cake1step2 = new Step(1,"Starting prep","1. Preheat the oven to 350\\u00b0F. Butter a 9\\\" deep dish pie pan.",
                "http:/", "http:/");
        Step cake1step3 = new Step(2,"Prep the cookie crust.",
                "2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. " +
                        "Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
                "");

        ArrayList<Step> stepList1 = new ArrayList<>();

        stepList1.add(cake1step1);
        stepList1.add(cake1step2);
        stepList1.add(cake1step3);

        Cake cake1 = new Cake(1, "Nutella Pie", 8, "", ingredientList1, stepList1);

        // CAKE 2

        Ingredient cake2ingredient1 = new Ingredient(350,"G", "Bittersweet chocolate (60-70% cacao)");
        Ingredient cake2ingredient2 = new Ingredient(226,"G", "unsalted butter");

        ArrayList<Ingredient> ingredientList2 = new ArrayList<>();

        ingredientList2.add(cake2ingredient1);
        ingredientList2.add(cake2ingredient2);


        Step cake2step1 = new Step(0,"Recipe Introduction","Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4",
                "");
        Step cake2step2 = new Step(1,"Starting prep","1. Preheat the oven to 350ï¿½F. Butter the bottom and sides of a 9\\\"x13\\\" pan.",
                "http:/", "http:/");
        Step cake2step3 = new Step(2,"Melt butter and bittersweet chocolate.",
                "2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
                "");
        Step cake2step4 = new Step(3,"Add sugars to wet mixture","3. Mix both sugars into the melted chocolate in a large mixing bowl until mixture is smooth and uniform.",
                "", "");
        Step cake2step5 = new Step(4,"Mix together dry ingredients","4. Sift together the flour, cocoa, and salt in a small bowl and whisk until mixture is uniform and no clumps remain.",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc9e_4-sift-flower-add-coco-powder-salt-brownies/4-sift-flower-add-coco-powder-salt-brownies.mp4",
                "http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg");

        ArrayList<Step> stepList2 = new ArrayList<>();

        stepList2.add(cake2step1);
        stepList2.add(cake2step2);
        stepList2.add(cake2step3);
        stepList2.add(cake2step4);
        stepList2.add(cake2step5);

        Cake cake2 = new Cake(2, "Brownies", 8, "http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg", ingredientList2, stepList2);


        // CAKE 3

        Ingredient cake3ingredient1 = new Ingredient(400,"G", "sifted cake flour");
        Ingredient cake3ingredient2 = new Ingredient(700,"G", "granulated sugar");
        Ingredient cake3ingredient3 = new Ingredient(4,"TSP", "baking powder");
        Ingredient cake3ingredient4 = new Ingredient(1.5,"TSP", "salt");
        Ingredient cake3ingredient5 = new Ingredient(2,"TBLSP", "vanilla extract, divided");
        Ingredient cake3ingredient6 = new Ingredient(8,"UNIT", "egg yolks");

        ArrayList<Ingredient> ingredientList3 = new ArrayList<>();

        ingredientList3.add(cake3ingredient1);
        ingredientList3.add(cake3ingredient2);
        ingredientList3.add(cake3ingredient3);
        ingredientList3.add(cake3ingredient4);
        ingredientList3.add(cake3ingredient5);
        ingredientList3.add(cake3ingredient6);


        Step cake3step1 = new Step(0,"Recipe Introduction","Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffddf0_-intro-yellow-cake/-intro-yellow-cake.mp4",
                "");

        ArrayList<Step> stepList3 = new ArrayList<>();
        stepList3.add(cake3step1);

        Cake cake3 = new Cake(3, "Yellow Cake", 8, "", ingredientList3, stepList3);

        ArrayList<Step> stepList4 = new ArrayList<>();
        ArrayList<Ingredient> ingredientList4 = new ArrayList<>();

        Cake cake4 = new Cake(4, "Phantom Cake" , 0, "", ingredientList4, stepList4);

        cakeList.add(cake1);
        cakeList.add(cake2);
        cakeList.add(cake3);
        cakeList.add(cake4);

        return cakeList;
    }

}
