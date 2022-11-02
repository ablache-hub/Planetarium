package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alex.planets.adapter.RecyclingListAdapter;
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

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "solarSystem.json");

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Planet>>() {
        }.getType();

        List<Planet> planets = gson.fromJson(jsonFileString, listUserType);

        Utils.savePlanet(planets, getApplicationContext());


/*        for (int i = 0; i < planets.size(); i++) {
            if (planets.get(i).getAroundPlanet() != null) {
                Log.i("data", "> Item " + i + "\n" + planets.get(i).getName() + " est un satellite de: " + planets.get(i).getAroundPlanet());
            } else {
                Log.i("data", "> Item " + i + "\n" + "Satellites de " + planets.get(i).getName() + ": " + planets.get(i).getMoons());
            }
        }*/

        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        RecyclingListAdapter adapter = new RecyclingListAdapter(planets, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
}