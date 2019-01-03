package com.example.gaiajustin.bakingapp.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

// This is a sharedViewModel
public class CakeViewModel extends AndroidViewModel {

    private CakeRepository repository;
    private LiveData<List<Cake>> cakeList;

    public CakeViewModel(@NonNull Application application) {
        super(application);
        repository = new CakeRepository(application);
        cakeList = repository.getCakeList();
    }

    public LiveData<List<Cake>> getCakes() {
        return cakeList;
    }

    public void insert(Cake cake) {
        repository.insert(cake);
    }

    public void delete(Cake cake) {
        repository.delete(cake);
    }

    public void update(Cake cake) {
        repository.update(cake);
    }

    public void insertCakeList(ArrayList<Cake> cakes) {
        for (Cake cake: cakes) {
            insert(cake);
        }
    }
}