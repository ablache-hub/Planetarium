package com.alex.planets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.alex.planets.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayoutPlanets;
    public ViewPager2 viewPagerFactors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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