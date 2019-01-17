package com.example.gaiajustin.bakingapp.Step;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class StepViewModel extends ViewModel {

    private MutableLiveData<Integer> currentStep = new MutableLiveData<>();
    private MutableLiveData<Integer> stepSize = new MutableLiveData<>();

    public void setCurrentStep(Integer newStep) {
        currentStep.setValue(newStep);
    }

    public void setStepSize(Integer step) {
        stepSize.setValue(step);
    }

    public LiveData<Integer> getStepSize() {return stepSize;}
    public LiveData<Integer> getStep() {
        return currentStep;
    }

}
