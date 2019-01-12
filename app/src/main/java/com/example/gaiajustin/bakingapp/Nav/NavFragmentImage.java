package com.example.gaiajustin.bakingapp.Nav;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.gaiajustin.bakingapp.DetailActivity;
import com.example.gaiajustin.bakingapp.NavActivity;
import com.example.gaiajustin.bakingapp.ImageRequester;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NavFragmentImage extends Fragment {

    private static final String TAG = NavFragmentImage.class.getSimpleName();
    private CakeViewModel cakeViewModel;
    private List<Cake> cakeList;
    private ImageRequester imageRequester;

    // Views
    private NetworkImageView imageView;
    private CardView cardViewIngredient;
    private ImageView widgetImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ba_nav_image, container, false);

        imageRequester = ImageRequester.getInstance();

        // Set up Views
        imageView = view.findViewById(R.id.nav_image);
        widgetImageView = view.findViewById(R.id.nav_widgetStar);

        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeList = new ArrayList<>();
        
        cardViewIngredient = view.findViewById(R.id.nav_ingredientsCard);
        
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        cardViewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetailIntent = new Intent(getContext(), DetailActivity.class);
                Intent intent = getActivity().getIntent();
                int cake_position = intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0);
                toDetailIntent.putExtra(getResources().getString(R.string.cake_position_pressed), cake_position);
                getContext().startActivity(toDetailIntent);
            }
        });

        widgetImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        final int cakeId = intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0);
        // TODO is it safe to remove this line?
//        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                cakeList.clear();
                cakeList.addAll(cakes);
                ((NavActivity) getActivity()).setActionBarTitle(cakeList.get(cakeId).getName());
                imageRequester.setImageFromUrl(imageView, cakeList.get(cakeId).getImageURL());
            }
        });
    }

    private void setImageStar(boolean isFav) {
        if (isFav) {
            widgetImageView.setImageResource(R.drawable.star);
        } else {
            widgetImageView.setImageResource(R.drawable.nostar);
        }
    }



}
