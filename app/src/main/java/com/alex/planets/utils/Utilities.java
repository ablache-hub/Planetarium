package com.alex.planets.utils;

import android.util.Log;

import com.alex.planets.Planet;

import java.util.ArrayList;
import java.util.List;

public final class Utilities {

    public static List<Planet> getPlanetOrNotList(List<Planet> planets, Boolean isPlanet) {
        List<Planet> newList = new ArrayList<>();

        for (Planet planet : planets) {
            if (planet.isPlanet() == isPlanet) {
                newList.add(planet);
                Log.v("TAG", "" + planet.getName());
            }
        }
        return newList;
    }
}
