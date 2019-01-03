package com.example.gaiajustin.bakingapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaiajustin.bakingapp.CakeGrid.ProductGridFragment;
import com.example.gaiajustin.bakingapp.Details.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DetailFragment detailFragment = new DetailFragment();
        FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
        fragmentTransactionFav.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransactionFav.replace(R.id.mFragMain, detailFragment);
        fragmentTransactionFav.commit();
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
