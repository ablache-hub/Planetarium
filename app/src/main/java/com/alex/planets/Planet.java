package com.alex.planets;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Planet implements Serializable {
    String searchId;
    String name;
    Boolean isPlanet;

    public Boolean isPlanet() {
        return isPlanet;
    }


    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "searchId='" + searchId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
