package com.alex.planets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alex.planets.R;
import com.alex.planets.adapters.RecyclingListPlanetAdapter;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;

import java.util.List;

public class PlanetFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclingListPlanetAdapter adapter;
    private final Boolean isPlanet;

    public PlanetFragment(Boolean isPlanet) {
        this.isPlanet = isPlanet;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_planet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rvPlanets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclingListPlanetAdapter(null, getContext());
        recyclerView.setAdapter(adapter);

        PlanetDao planetDao = DatabaseClient.getInstance(getContext()).getAppDatabase().planetDao();
        LiveData<List<Planet>> planetList = planetDao.getIsPlanet(isPlanet);

        planetList.observe(getViewLifecycleOwner(), new Observer<List<Planet>>() {
            @Override
            public void onChanged(List<Planet> planets) {
                if(!planets.isEmpty()) {
                    adapter = new RecyclingListPlanetAdapter(planets, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }


}