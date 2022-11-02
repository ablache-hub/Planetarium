package com.alex.planets.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.planets.models.Planet;

import java.util.List;

@Dao
public interface PlanetDao {
    @Query("SELECT * FROM planets")
    List<Planet> getAll();

    @Insert
    void insert(Planet planet);

    @Delete
    void delete(Planet planet);

    @Update
    void update(Planet planet);
}

