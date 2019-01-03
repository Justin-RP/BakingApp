package com.example.gaiajustin.bakingapp.Details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.gaiajustin.bakingapp.CakeGrid.ProductCardRecyclerViewAdapter;
import com.example.gaiajustin.bakingapp.CakeGrid.ProductGridItemDecoration;
import com.example.gaiajustin.bakingapp.DetailActivity;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private static final String TAG = DetailFragment.class.getSimpleName();
    private CakeViewModel cakeViewModel;
    private List<Cake> cakeList;

    // Views
    private NetworkImageView imageView;
    private LinearLayout linearLayout;
    private TextView tvCakeName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ba_detail_fragment, container, false);

        // Set up Views
        imageView = view.findViewById(R.id.detail_image);
        linearLayout = view.findViewById(R.id.detail_linearLayout);
        tvCakeName = view.findViewById(R.id.detail_title);

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
                Log.d(TAG, "onChanged: " + cakeList.get(0).getName());

            }
        });
    }



}
