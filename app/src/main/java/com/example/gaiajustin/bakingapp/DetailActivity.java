package com.example.gaiajustin.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.gaiajustin.bakingapp.Ingredient.IngredientFragment;
import com.example.gaiajustin.bakingapp.Step.MediaPlayerFragment;
import com.example.gaiajustin.bakingapp.Step.StepInstructionFragment;
import com.example.gaiajustin.bakingapp.Step.StepNavFragment;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    LinearLayout stepLinearLayout;
    FrameLayout ingredientLayout, frameInstruction, frameMedia, frameNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        stepLinearLayout = findViewById(R.id.detailStepLayout);
        ingredientLayout = findViewById(R.id.detailFragIngredient);
        frameInstruction = findViewById(R.id.detailFrameInstruction);
        frameMedia = findViewById(R.id.detailFrameMedia);
        frameNav = findViewById(R.id.detailFrameNav);

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

            fragmentTransactionFav.replace(frameInstruction.getId(), stepFragment);
            fragmentTransactionFav.replace(frameMedia.getId(), mediaPlayerFragment);
            fragmentTransactionFav.replace(frameNav.getId(), stepNavFragment);
            fragmentTransactionFav.commit();
            isIngredientLayoutShown(false);

//            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                // Show all
//                Log.d(TAG, "onCreate: Orientation: Landscape");
//                isFullScreen(true);
//            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                // Remove all except nav
//                Log.d(TAG, "onCreate: Orientation Portrait");
//                isFullScreen(false);
//            } else {
//                Log.e(TAG, "onCreate: Orientation" + getResources().getConfiguration().orientation );
//            }

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

    public void isIngredientLayoutShown(boolean ingredientShown) {
        if (ingredientShown) {
            ingredientLayout.setVisibility(View.VISIBLE);
            stepLinearLayout.setVisibility(View.GONE);
        } else {
            ingredientLayout.setVisibility(View.GONE);
            stepLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void isFullScreen(boolean fullScreenShown) {
        if(fullScreenShown) {
            frameNav.setVisibility(View.GONE);
            frameInstruction.setVisibility(View.GONE);
        } else {
            frameNav.setVisibility(View.VISIBLE);
            frameMedia.setVisibility(View.VISIBLE);
        }
    }
}
