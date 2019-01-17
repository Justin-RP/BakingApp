package com.example.gaiajustin.bakingapp.Step;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaiajustin.bakingapp.R;

public class StepNavFragment extends Fragment {

    private static final String TAG = StepNavFragment.class.getSimpleName();

    public static StepNavFragment newInstance() {
        return new StepNavFragment();
    }

    private StepViewModel stepViewModel;

    private int currentStep;
    private int maxStep;

    private TextView tvStepNav;
    private ImageView ivPrev, ivNext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_nav, container, false);

        tvStepNav = view.findViewById(R.id.stepNav_text);
        ivPrev = view.findViewById(R.id.stepNav_prev);
        ivNext = view.findViewById(R.id.stepNav_next);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stepViewModel = ViewModelProviders.of(getActivity()).get(StepViewModel.class);
        stepViewModel.getStepSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stepSize) {
                maxStep = stepSize - 1;
                Log.d(TAG, "onChanged: stepSize currentStep " + currentStep);
                Log.d(TAG, "onChanged: stepSize maxStep " + maxStep);

                checkMaxStep();

            }
        });
        stepViewModel.getStep().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                currentStep = integer;


                if (currentStep == 0) {
                    showPrevButton(false);
                    tvStepNav.setText("Introduction");
                } else {
                    showPrevButton(true);
                    tvStepNav.setText(String.valueOf(integer));
                }

                checkMaxStep();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        tvStepNav.setText("Originate");
//        final int currentStep = stepViewModel.getStep();
        ivPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Prev");
                int previousStep = currentStep - 1;
                stepViewModel.setCurrentStep(previousStep);
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Next");
                int nextStep = currentStep + 1;
                stepViewModel.setCurrentStep(nextStep);
            }
        });
    }

    private void showPrevButton(boolean isShown) {
        if (isShown) {
            ivPrev.setVisibility(View.VISIBLE);
        } else {
            ivPrev.setVisibility(View.GONE);
        }
    }

    private void showNextButton(boolean isShown) {
        if (isShown) {
            ivNext.setVisibility(View.VISIBLE);
        } else {
            ivNext.setVisibility(View.GONE);
        }
    }

    private void checkMaxStep() {
        if (currentStep == maxStep) {
            Log.d(TAG, "onChanged: TESTING");
            showNextButton(false);
        } else {
            Log.d(TAG, "onChanged: WHY");
            Log.d(TAG, "onChanged: getStep currentStep " + currentStep);
            Log.d(TAG, "onChanged: getStep maxStep " + maxStep);
            showNextButton(true);
        }
    }
}

