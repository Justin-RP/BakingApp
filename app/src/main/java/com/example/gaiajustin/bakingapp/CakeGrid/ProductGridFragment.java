package com.example.gaiajustin.bakingapp.CakeGrid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaiajustin.bakingapp.NavActivity;
import com.example.gaiajustin.bakingapp.MyApplication;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductGridFragment extends Fragment {

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    private static final String TAG = ProductGridFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private CakeViewModel cakeViewModel;
    private List<Cake> cakeList;
    private Context context;
    boolean isTablet;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MyApplication.getAppContext();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ba_product_grid_fragment, container, false);
        Log.d(TAG, "onCreateView: FRAGMENT CALLED");

        // Instantiate cakeList
        cakeList = new ArrayList<>();
        isTablet = getResources().getBoolean(R.bool.isTablet);
        recyclerView = view.findViewById(R.id.recycler_view);

        // Set up the RecyclerView
        if (isTablet) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        } else  {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        }


        recyclerView.setHasFixedSize(true);
        final ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                cakeList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.ba_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));


        // Set up viewModel

        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
//        cakeViewModel.insertCakeList(Cake.initProductEntryList());
        cakeViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                // TODO Check if CakeList Observed is only supposed to activate once
                Log.d(TAG, "onChanged: CakeList Observed");
                cakeList.clear();
                cakeList.addAll(cakes);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d(TAG, "onItemClick: " + position);
                        Intent toNavIntent = new Intent(context, NavActivity.class);

                        toNavIntent.putExtra(getResources().getString(R.string.cake_position_pressed), position);
                        toNavIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(toNavIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // TODO do whatever / Delete
                    }
                })
        );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

}
