package com.alex.planets.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "planets")
public class Planet implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "searchId")
    String searchId;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "isPlanet")
    Boolean isPlanet;

    @ColumnInfo(name = "image")
    String image;

    //    AroundPlanet aroundPlanet;
    //    List<Moons> moons;

    public void setId(int id) {
        this.id = id;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsPlanet(Boolean planet) {
        isPlanet = planet;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

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

//    public String getAroundPlanet() {
//        if (aroundPlanet != null) {
//            return aroundPlanet.getPlanet();
//        } else {
//            return null;
//        }
//    }
//
//    public List<String> getMoons() {
//        List<String> moonsList = new ArrayList<>();
//        if (moons != null) {
//            for (Moons moon : moons) {
//                moonsList.add(moon.getMoon());
//            }
//        }
//        return moonsList;
//    }
//    public Moons getMoon() {
//        if(moons != null) {
//            return moons;
//        } else {
//            return new Moons(null);
//        }
//    }
}
