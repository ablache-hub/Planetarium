package com.alex.planets.utils;

import androidx.room.TypeConverter;

import com.alex.planets.models.AroundPlanet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AroundPlanetConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static AroundPlanet stringToSomeObjectList(String data) {
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<AroundPlanet>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(AroundPlanet someObjects) {
        return gson.toJson(someObjects);
    }
}
