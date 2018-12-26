package com.example.gaiajustin.bakingapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaiajustin.bakingapp.CakeGrid.ProductGridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductGridFragment productGridFragment = new ProductGridFragment();
        FragmentTransaction fragmentTransactionFav = getSupportFragmentManager().beginTransaction();
        fragmentTransactionFav.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransactionFav.replace(R.id.mFragMain, productGridFragment);
        fragmentTransactionFav.commit();
    }


}
