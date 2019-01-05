package com.example.gaiajustin.bakingapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaiajustin.bakingapp.Details.DetailFragmentImage;
import com.example.gaiajustin.bakingapp.Details.DetailGridFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DetailFragmentImage detailFragmentImage = new DetailFragmentImage();
        DetailGridFragment detailGridFragment = new DetailGridFragment();
        FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
        fragmentTransactionFav.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransactionFav.replace(R.id.mFragDetail_image, detailFragmentImage);
        fragmentTransactionFav.replace(R.id.mFragDetail_details, detailGridFragment);
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
