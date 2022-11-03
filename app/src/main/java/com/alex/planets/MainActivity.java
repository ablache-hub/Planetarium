package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alex.planets.adapter.RecyclingListAdapter;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;
import com.alex.planets.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlanetDao planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();

        LiveData<List<Planet>> planetList = planetDao.getAll();

        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        RecyclingListAdapter adapter = new RecyclingListAdapter(null, MainActivity.this);
        recyclerView.setAdapter(adapter);

        planetList.observe(this, new Observer<List<Planet>>() {
            @Override
            public void onChanged(List<Planet> planets) {

                if (planets.size() == 0) {
                    Gson gson = new Gson();
                    Type listUserType = new TypeToken<List<Planet>>() {
                    }.getType();

                    String jsonFileString = Utils.getJsonToString(getApplicationContext(), "solarSystem.json");

                    List<Planet> planetsFromJson = gson.fromJson(jsonFileString, listUserType);

                    Utils.savePlanet(planetsFromJson, getApplicationContext());
                } else {
                    RecyclingListAdapter adapter = new RecyclingListAdapter(planets, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
    }
}