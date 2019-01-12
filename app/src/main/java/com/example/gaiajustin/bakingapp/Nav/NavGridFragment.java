package com.example.gaiajustin.bakingapp.Nav;

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

import com.example.gaiajustin.bakingapp.DetailActivity;
import com.example.gaiajustin.bakingapp.MyApplication;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;
import com.example.gaiajustin.bakingapp.database.Step;

import java.util.ArrayList;
import java.util.List;

public class NavGridFragment extends Fragment {

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    private static final String TAG = NavGridFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private CakeViewModel cakeViewModel;
    private List<Step> stepList;
    private Context context;
    private int cakeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MyApplication.getAppContext();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment with the StepGrid theme
        View view = inflater.inflate(R.layout.ba_nav_grid_fragment, container, false);

        // Instantiate cakeList
        stepList = new ArrayList<>();

        // Set up the RecyclerView
        recyclerView = view.findViewById(R.id.nav_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        final NavCardRecyclerViewAdapter adapter = new NavCardRecyclerViewAdapter(
                stepList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.ba_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new NavGridItemDecoration(largePadding, smallPadding));


        // Set up viewModel
        // TODO UPDATE LIST
        cakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                Intent intent = getActivity().getIntent();
                cakeId = intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0);
                // TODO Check if CakeList Observed is only supposed to activate once
//                Log.d(TAG, "onChanged: CakeList Observed");
                stepList.clear();
                stepList.addAll(cakes.get(cakeId).getSteps());
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(
                new NavItemClickListener(context, recyclerView ,new NavItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO When Recycler View is clicked Expand
                        Log.d(TAG, "onItemClick: " + position);
                        Intent toDetailIntent = new Intent(context, DetailActivity.class);
                        toDetailIntent.putExtra(getResources().getString(R.string.step_position_pressed), position);
                        toDetailIntent.putExtra(getResources().getString(R.string.cake_position_pressed), cakeId);
                        context.startActivity(toDetailIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // TODO do whatever / Delete
                    }
                })
        );
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
