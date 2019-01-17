package com.example.gaiajustin.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.gaiajustin.bakingapp.Ingredient.IngredientFragment;
import com.example.gaiajustin.bakingapp.Step.MediaPlayerFragment;
import com.example.gaiajustin.bakingapp.Step.StepInstructionFragment;
import com.example.gaiajustin.bakingapp.Step.StepNavFragment;

public class DetailActivity extends AppCompatActivity {

    LinearLayout stepLinearLayout;
    FrameLayout ingredientLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        stepLinearLayout = findViewById(R.id.detailStepLayout);
        ingredientLayout = findViewById(R.id.detailFragIngredient);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent.hasExtra(getResources().getString(R.string.step_position_pressed))) {
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.cake_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0));
            bundle.putInt(getResources().getString(R.string.step_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.step_position_pressed), 0));

            FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
            StepInstructionFragment stepFragment = new StepInstructionFragment();
            MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
            StepNavFragment stepNavFragment = new StepNavFragment();

            stepFragment.setArguments(bundle);

            fragmentTransactionFav.replace(R.id.detailFragInstruction, stepFragment);
            fragmentTransactionFav.replace(R.id.detailFragMedia, mediaPlayerFragment);
            fragmentTransactionFav.replace(R.id.detailFragNav, stepNavFragment);
            fragmentTransactionFav.commit();
            isIngredientLayoutShown(false);

        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.cake_position_pressed),
                    intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0));
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments(bundle);
            FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
            fragmentTransactionFav.replace(R.id.detailFragIngredient, ingredientFragment);
            fragmentTransactionFav.commit();
            isIngredientLayoutShown(true);
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

    public void isIngredientLayoutShown(boolean isTrue) {
        if (isTrue) {
            ingredientLayout.setVisibility(View.VISIBLE);
            stepLinearLayout.setVisibility(View.GONE);
        } else {
            ingredientLayout.setVisibility(View.GONE);
            stepLinearLayout.setVisibility(View.VISIBLE);
        }
    }
}
