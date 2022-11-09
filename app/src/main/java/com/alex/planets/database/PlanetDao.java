package com.alex.planets.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.planets.models.Planet;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface PlanetDao {
    @Query("SELECT * FROM planets")
    LiveData<List<Planet>> getAll();

    @Query("SELECT * FROM planets WHERE isPlanet= :planetOrNot")
    LiveData<List<Planet>> getIsPlanet(Boolean planetOrNot);

    @Insert
    void insert(Planet planet);

    @Delete
    void delete(Planet planet);

    @Update
    void update(Planet planet);
}

