package com.alex.planets;

import com.alex.planets.models.AroundPlanet;
import com.alex.planets.models.Moons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Planet implements Serializable {
    String searchId;
    String name;
    Boolean isPlanet;
    AroundPlanet aroundPlanet;
    List<Moons> moons;
    String image;

    public String getImage() {
        return image;
    }

    public Boolean isPlanet() {
        return isPlanet;
    }

    public String getSearchId() {
        return searchId;
    }

    public String getName() {
        return name;
    }

    public String getAroundPlanet() {
        if (aroundPlanet != null) {
            return aroundPlanet.getPlanet();
        } else {
            return null;
        }
    }

    public List<String> getMoons() {
        List<String> moonsList = new ArrayList<>();
        if (moons != null) {
            for (Moons moon : moons) {
                moonsList.add(moon.getMoon());
            }
        }
        return moonsList;
    }
//    public Moons getMoon() {
//        if(moons != null) {
//            return moons;
//        } else {
//            return new Moons(null);
//        }
//    }
}
