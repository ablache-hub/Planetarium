package com.alex.planets.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class AroundPlanet {

    String planet;

    public AroundPlanet(String planet) {
        this.planet = planet;
    }

    public String getPlanet() {
        return planet;
    }
}
