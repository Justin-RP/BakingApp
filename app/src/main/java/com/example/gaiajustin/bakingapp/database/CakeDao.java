package com.example.gaiajustin.bakingapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CakeDao {

    @Query("SELECT * FROM cake_table")
    LiveData<List<Cake>> loadAllCakes();

    @Insert
    void insertCake(Cake cake);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCake(Cake cake);

    @Delete
    void deleteCake(Cake cake);

}
