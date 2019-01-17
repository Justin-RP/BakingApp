package com.example.gaiajustin.bakingapp.Step;

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
import android.widget.TextView;

import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;
import com.example.gaiajustin.bakingapp.database.CakeViewModel;
import com.example.gaiajustin.bakingapp.database.Step;

import java.util.ArrayList;
import java.util.List;

public class StepInstructionFragment extends Fragment {

    private static final String TAG = StepInstructionFragment.class.getSimpleName();

    private CakeViewModel mViewModel;
    private List<Cake> cakeList;
    private TextView tvDescription;

    private int stepId;
    private int cakeId;

    private StepViewModel stepViewModel;

    public static StepInstructionFragment newInstance() {
        return new StepInstructionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.step_instruction_fragment, container, false);

        // Initialize
        mViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);
        cakeList = new ArrayList<>();

        tvDescription = view.findViewById(R.id.instruction_description);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        cakeId = intent.getIntExtra(getResources().getString(R.string.cake_position_pressed), 0);
        stepId = intent.getIntExtra(getResources().getString(R.string.step_position_pressed), 0);
        mViewModel.getCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(@Nullable List<Cake> cakes) {
                Log.d(TAG, "onChanged: " + cakeId + " : " + stepId);

                Cake cake = cakes.get(cakeId);
                Step step = cake.getSteps().get(stepId);
                cakeList.clear();
                cakeList.addAll(cakes);
                stepViewModel.setCurrentStep(stepId);
                stepViewModel.setStepSize(cake.getSteps().size());
//                Log.d(TAG, "onChanged: " + step.getDesc());

//                ((NavActivity) getActivity()).setActionBarTitle(cakeList.get(cakeId).getName());
                tvDescription.setText(step.getDesc());
            }
        });
        stepViewModel = ViewModelProviders.of(getActivity()).get(StepViewModel.class);
        stepViewModel.getStep().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                Cake cake = cakeList.get(cakeId);

                tvDescription.setText(cake.getSteps().get(integer).getDesc());
            }
        });
    }

}
