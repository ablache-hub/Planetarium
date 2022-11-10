package com.alex.planets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.alex.planets.adapters.ViewPagerAdapter;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;
import com.alex.planets.utils.Utils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayoutPlanets;
    public ViewPager2 viewPagerFactors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlanetDao planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        LiveData<List<Planet>> planetList = planetDao.getAll();

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
                }
            }
        });


        tabLayoutPlanets = findViewById(R.id.tabLayout);
        viewPagerFactors = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPagerFactors.setAdapter(viewPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayoutPlanets,
                viewPagerFactors,
                true,
                true,
                new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Planètes");
                        break;
                    case 1:
                        tab.setText("Satellites");
                }
            }
        });

        tabLayoutMediator.attach();
    }
}