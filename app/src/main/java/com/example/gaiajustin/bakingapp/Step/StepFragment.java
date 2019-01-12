package com.example.gaiajustin.bakingapp.Step;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;

import java.util.ArrayList;
import java.util.List;

public class StepFragment extends Fragment {

    private static final String TAG = StepFragment.class.getSimpleName();

    private CakeViewModel mViewModel;
    private TextView tvCakeStep;
    private List<Cake> cakeList;

    public static StepFragment newInstance() {
        return new StepFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.step_fragment, container, false);

        // Initialize
        tvCakeStep = view.findViewById(R.id.step_cakeStep);
        mViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeList = new ArrayList<>();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        final int cakeId = intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0);
        final int stepId = intent.getIntExtra(getResources().getString(R.string.step_position_pressed), 0);
        mViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                cakeList.clear();
                cakeList.addAll(cakes);

//                ((NavActivity) getActivity()).setActionBarTitle(cakeList.get(cakeId).getName());

                tvCakeStep.setText(cakeList.get(cakeId).getSteps().get(stepId).getDesc());
            }
        });
        // TODO: Use the ViewModel
    }

}
