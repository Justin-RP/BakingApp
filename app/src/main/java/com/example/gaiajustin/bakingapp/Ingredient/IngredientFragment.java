package com.example.gaiajustin.bakingapp.Ingredient;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaiajustin.bakingapp.DetailActivity;
import com.example.gaiajustin.bakingapp.MyApplication;
import com.example.gaiajustin.bakingapp.Nav.NavGridItemDecoration;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;
import com.example.gaiajustin.bakingapp.database.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    private static final String TAG = IngredientFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private CakeViewModel cakeViewModel;
    private List<Ingredient> ingredientList;
    private Context context;
    private IngredientRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the StepGrid theme
        View view = inflater.inflate(R.layout.ba_ingredient_fragment, container, false);
        Log.d(TAG, "onCreateView: ");


        context = MyApplication.getAppContext();

        // Instantiate cakeList
        ingredientList = new ArrayList<>();

        // Set up the RecyclerView
        recyclerView = view.findViewById(R.id.ingredient_recycler_view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        adapter = new IngredientRecyclerViewAdapter(
                ingredientList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.ba_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new NavGridItemDecoration(largePadding, smallPadding));

        // Set up viewModel
        // TODO UPDATE LIST
        cakeViewModel = ViewModelProviders.of(getActivity()).get(CakeViewModel.class);
        cakeViewModel.getCakes().observe(getActivity(), new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                int cake_position = getArguments().getInt(getResources().getString(R.string.cake_position_pressed),0);
                Log.d(TAG, "onChanged: " + cake_position);
                Log.d(TAG, "onChanged: " + cakes.get(cake_position).getIngredients().get(0).getIngredient());
                ingredientList.clear();
                ingredientList.addAll(cakes.get(cake_position).getIngredients());
                ((DetailActivity) getActivity()).setActionBarTitle("Ingredient");
                adapter.notifyDataSetChanged();
            }
        });

    }
}
