package com.alex.planets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.planets.MainActivity;
import com.alex.planets.R;
import com.alex.planets.adapters.RecyclingListAdapter;
import com.alex.planets.adapters.RecyclingListPlanetAdapter;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;

import java.util.List;

public class PlanetFragment extends Fragment {

    public RecyclerView recyclerView;
    public RecyclingListPlanetAdapter adapter;

    public static PlanetFragment newInstance() {
        return new PlanetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rcPlanets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclingListPlanetAdapter(null, getContext());

        PlanetDao planetDao = DatabaseClient.getInstance(this.getContext()).getAppDatabase().planetDao();
        TextView test = view.findViewById(R.id.tvTest);
        LiveData<List<Planet>> listTest = planetDao.getIsPlanet(true);
        test.setText("alright");

        listTest.observe(getViewLifecycleOwner(), new Observer<List<Planet>>() {
            @Override
            public void onChanged(List<Planet> planets) {
                if(!planets.isEmpty()) {
                    RecyclingListAdapter adapter = new RecyclingListAdapter(planets, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }


}