package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Moons;
import com.alex.planets.models.Planet;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String test = intent.getStringExtra("planetName");
        TextView textView = findViewById(R.id.textViewDetails1);
        TextView textView1 = findViewById(R.id.textViewDetails2);

        PlanetDao planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        LiveData<Planet> planet = planetDao.getPlanet(test);

        planet.observe(this, new Observer<Planet>() {
            @Override
            public void onChanged(Planet planet) {
                textView.setText(planet.getName());
                textView1.setText(planet.getEnglishName());
                List<Moons> moonList = planet.getMoons();
                for (Moons moon : moonList) {
                    Log.v("test", moon.getMoon());
                }
            }
        });
    }
}