package com.example.gaiajustin.bakingapp.Details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.example.gaiajustin.bakingapp.DetailActivity;
import com.example.gaiajustin.bakingapp.ImageRequester;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFragmentImage extends Fragment {

    private static final String TAG = DetailFragmentImage.class.getSimpleName();
    private CakeViewModel cakeViewModel;
    private List<Cake> cakeList;
    private ImageRequester imageRequester;

    // Views
    private NetworkImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ba_detail_image_fragment, container, false);

        imageRequester = ImageRequester.getInstance();

        // Set up Views
        imageView = view.findViewById(R.id.detail_image);

        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeList = new ArrayList<>();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        final int cakeId = intent.getIntExtra(getResources().getString(R.string.cake_positon_pressed), 0);
        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                cakeList.clear();
                cakeList.addAll(cakes);
                ((DetailActivity) getActivity()).setActionBarTitle(cakeList.get(cakeId).getName());
                imageRequester.setImageFromUrl(imageView, cakeList.get(cakeId).getImageURL());
            }
        });
    }



}
