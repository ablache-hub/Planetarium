package com.alex.planets.database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient mInstance;

    // Objet BDD
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        // Creation BDD avec le room builder
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "PlanetApp").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
