package com.alex.planets.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alex.planets.models.Planet;

@Database(entities = {Planet.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlanetDao planetDao();
}
