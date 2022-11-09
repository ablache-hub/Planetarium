package com.alex.planets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.alex.planets.adapters.RecyclingListAdapter;
import com.alex.planets.adapters.ViewPagerAdapterPlanets;
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

    public PlanetDao planetDao;
    public LiveData<List<Planet>> planetList;
    public RecyclerView recyclerView;
    public RecyclingListAdapter adapter;
    public TabLayout tabLayoutPlanets;
    public ViewPager2 viewPagerFactors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayoutPlanets = findViewById(R.id.tabLayout);
        viewPagerFactors = findViewById(R.id.viewPager2);

        ViewPagerAdapterPlanets viewPagerAdapterPlanets = new ViewPagerAdapterPlanets(getSupportFragmentManager(), getLifecycle());
        viewPagerFactors.setAdapter(viewPagerAdapterPlanets);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutPlanets, viewPagerFactors, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
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

 /*       planetDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().planetDao();
        planetList = planetDao.getAll();*/

/*        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RecyclingListAdapter(null, MainActivity.this);
        recyclerView.setAdapter(adapter);*/

/*        planetList.observe(this, new Observer<List<Planet>>() {
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
        });*/
    }
}