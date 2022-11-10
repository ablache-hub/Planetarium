package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;

public class DetailsActivity extends AppCompatActivity {

    private PlanetDao planetDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String test = intent.getStringExtra("planetName");
        TextView textView = findViewById(R.id.textViewDetails1);

        planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        LiveData<Planet> planet = planetDao.getPlanet(test);

        planet.observe(this.getApplicationContext());
    }
}