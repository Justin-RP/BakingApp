package com.example.gaiajustin.bakingapp.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IngredientConverter {
    @TypeConverter
    public static ArrayList<Ingredient> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<Ingredient> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}