package com.example.gaiajustin.bakingapp.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class CakeRepository {
    private CakeDao cakeDao;
    private LiveData<List<Cake>> cakeList;

    public LiveData<List<Cake>> getCakeList() {
        return cakeList;
    }

    public CakeRepository(Application application) {
        AppDatabase database = AppDatabase.getsInstance(application);
        cakeDao = database.cakeDao();
        cakeList = cakeDao.loadAllCakes();
    }

    public void insert(Cake cake) {
        new InsertCakeAsyncTask(cakeDao).execute(cake);
    }

    public void update(Cake cake) {
        new UpdateCakeAsyncTask(cakeDao).execute(cake);
    }

    public void delete(Cake cake) {
        new DeleteCakeAsyncTask(cakeDao).execute(cake);
    }

    private static class InsertCakeAsyncTask extends AsyncTask<Cake, Void, Void> {

        private CakeDao cakeDao;

        public InsertCakeAsyncTask(CakeDao cakeDao) {
            this.cakeDao = cakeDao;
        }

        @Override
        protected Void doInBackground(Cake... cakes) {
            this.cakeDao.insertCake(cakes[0]);
            return null;
        }
    }

    private static class UpdateCakeAsyncTask extends AsyncTask<Cake, Void, Void> {

        private CakeDao cakeDao;

        public UpdateCakeAsyncTask(CakeDao cakeDao) {
            this.cakeDao = cakeDao;
        }

        @Override
        protected Void doInBackground(Cake... cakes) {
            this.cakeDao.updateCake(cakes[0]);
            return null;
        }
    }

    private static class DeleteCakeAsyncTask extends AsyncTask<Cake, Void, Void> {

        private CakeDao cakeDao;

        public DeleteCakeAsyncTask(CakeDao cakeDao) {
            this.cakeDao = cakeDao;
        }

        @Override
        protected Void doInBackground(Cake... cakes) {
            this.cakeDao.deleteCake(cakes[0]);
            return null;
        }
    }


}
