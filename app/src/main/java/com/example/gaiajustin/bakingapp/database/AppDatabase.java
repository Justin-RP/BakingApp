package com.example.gaiajustin.bakingapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {Cake.class}, version = 1)
@TypeConverters({IngredientConverter.class, StepConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "cake_table";
    private static AppDatabase sInstance;

    public abstract CakeDao cakeDao();

    public static synchronized AppDatabase getsInstance(Context context) {
        if (sInstance == null) {
            Log.d(TAG, "getsInstance: Creating new database instance");
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        Log.d(TAG, "getsInstance: Getting the database instance");
        return sInstance;
    }

}
