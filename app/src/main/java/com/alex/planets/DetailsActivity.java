package com.alex.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.AroundPlanet;
import com.alex.planets.models.Moons;
import com.alex.planets.models.Planet;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String test = intent.getStringExtra("planetName");
        TextView textView = findViewById(R.id.textViewDetails1);
        TextView textView1 = findViewById(R.id.textViewDetails2);
        TextView textView3 = findViewById(R.id.textViewDetails3);
        TextView textView4 = findViewById(R.id.textViewDetails4);
        CircleImageView imageView = findViewById(R.id.detailsImage);


        PlanetDao planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        LiveData<Planet> planet = planetDao.getPlanet(test);

        planet.observe(this, new Observer<Planet>() {
            @Override
            public void onChanged(Planet planet) {
                List<Moons> moonList = planet.getMoons();
                AroundPlanet aroundPlanet = planet.getAroundPlanet();

                Glide.with(getApplicationContext()).load(planet.getImage()).into(imageView);
                textView.setText(planet.getName());
                textView1.setText(planet.getEnglishName());

                if(moonList != null) {
                    textView3.setText("Satellites");
                    Log.v("test", "" + moonList);
                } else {
                    if (aroundPlanet != null) {
                        textView3.setText("Tourne autour de");
                        Log.v("Around", "" + aroundPlanet);
                    } else {
                        textView3.setVisibility(View.INVISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
}