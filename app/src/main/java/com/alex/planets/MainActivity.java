package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alex.planets.adapter.CustomAdapter;
import com.alex.planets.utils.Utilities;
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
/*        for (int i = 0; i < planets.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + planets.get(i).getName());
        }*/

        Utilities.getPlanetOrNotList(planets, false);

        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        CustomAdapter adapter = new CustomAdapter(planets, MainActivity.this);
        recyclerView.setAdapter(adapter);

/*        ArrayAdapter<Planet> arrayAdapter
                = new ArrayAdapter<Planet>(this, android.R.layout.simple_list_item_1 , planets);

        listView.setAdapter(arrayAdapter);*/


    }
}