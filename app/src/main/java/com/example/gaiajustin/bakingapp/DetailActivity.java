package com.example.gaiajustin.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaiajustin.bakingapp.CakeGrid.ProductGridFragment;
import com.example.gaiajustin.bakingapp.Ingredient.IngredientFragment;
import com.example.gaiajustin.bakingapp.Step.StepFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent.hasExtra(getResources().getString(R.string.step_position_pressed))) {
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.cake_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0));
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(bundle);
            FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
            fragmentTransactionFav.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransactionFav.replace(R.id.mFragMain, stepFragment);
            fragmentTransactionFav.commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.cake_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0));
            bundle.putInt(getResources().getString(R.string.step_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.step_position_pressed), 0));
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments(bundle);
            FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
            fragmentTransactionFav.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransactionFav.replace(R.id.mFragMain, ingredientFragment);
            fragmentTransactionFav.commit();
        }
    }

    public void setActionBarTitle(String title) {
        setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
