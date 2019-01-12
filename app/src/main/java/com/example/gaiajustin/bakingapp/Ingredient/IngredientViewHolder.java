package com.example.gaiajustin.bakingapp.Ingredient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gaiajustin.bakingapp.R;

public class IngredientViewHolder extends RecyclerView.ViewHolder{

    private static final String TAG = IngredientViewHolder.class.getSimpleName();
    public TextView ingredientTitle;
    public TextView ingredientMeasure;
    public TextView ingredientQty;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG, "IngredientViewHolder: Constructor Called");
        ingredientTitle = itemView.findViewById(R.id.ingredient_title);
        ingredientMeasure = itemView.findViewById(R.id.ingredient_measure);
        ingredientQty = itemView.findViewById(R.id.ingredient_qty);
    }
}
