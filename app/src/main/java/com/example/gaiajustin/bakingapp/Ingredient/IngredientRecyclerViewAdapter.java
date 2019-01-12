package com.example.gaiajustin.bakingapp.Ingredient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Ingredient;

import java.util.List;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientViewHolder>{

    private static final String TAG = IngredientRecyclerViewAdapter.class.getSimpleName();
    private List<Ingredient> ingredientList;

    IngredientRecyclerViewAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ba_ingredient_card, parent, false);
        return new IngredientViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        if (ingredientList != null && position < ingredientList.size()) {
            // TODO DISPLAY TEXT HERE

            Ingredient ingredient = ingredientList.get(position);
            holder.ingredientTitle.setText(ingredient.getIngredient());
            holder.ingredientMeasure.setText(ingredient.getMeasure());
            holder.ingredientQty.setText(String.valueOf(ingredient.getQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
