package com.alex.planets.utils;

import androidx.room.TypeConverter;

import com.alex.planets.models.Moon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class MoonsConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Moon> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Moon>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Moon> someObjects) {
        return gson.toJson(someObjects);
    }
}
