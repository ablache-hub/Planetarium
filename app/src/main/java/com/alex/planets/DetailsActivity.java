package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.AroundPlanet;
import com.alex.planets.models.Moon;
import com.alex.planets.models.Planet;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String test = intent.getStringExtra("planetName");
        TextView textView = findViewById(R.id.planetNameDetails);
        TextView textView3 = findViewById(R.id.textViewDetails3);
        TextView textView4 = findViewById(R.id.subtitleDetails);
        CircleImageView imageView = findViewById(R.id.detailsImage);
        ListView detailsList = findViewById(R.id.detailsList);

        PlanetDao planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        LiveData<Planet> planet = planetDao.getPlanet(test);

        planet.observe(this, new Observer<Planet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Planet planet) {
                List<Moon> moonList = planet.getMoons();
                AroundPlanet aroundPlanet = planet.getAroundPlanet();

                Glide.with(getApplicationContext()).load(planet.getImage()).into(imageView);
                textView.setText(planet.getName());

                if (moonList != null) {
                    List<String> moonNamesList = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, moonNamesList);

                    for (Moon moon : moonList) {
                        moonNamesList.add(moon.getMoon());
                    }

                    detailsList.setAdapter(adapter);
                    textView3.setText("Satellites");
                }

                if (aroundPlanet != null) {
                    String aroundPlanetName = aroundPlanet.getPlanet();
                    textView3.setText("Tourne autour de");
                    textView4.setText(aroundPlanetName.substring(0, 1).toUpperCase() + aroundPlanetName.substring(1));
                }
            }
        });
    }
}