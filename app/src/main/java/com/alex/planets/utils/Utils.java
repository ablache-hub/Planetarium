package com.alex.planets.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.alex.planets.MainActivity;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.models.Planet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String getJsonFromAssets(Context context, String jsonFile) {
        String jsonInString;
        try {
            InputStream is = context.getAssets().open(jsonFile);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonInString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonInString;
    }

    public static List<Planet> getPlanetOrNotList(List<Planet> planets, Boolean isPlanet) {
        List<Planet> newList = new ArrayList<>();

        for (Planet planet : planets) {
            if (planet.isPlanet() == isPlanet) {
                newList.add(planet);
            }
        }
        return newList;
    }

    public static void savePlanet(List<Planet> planets, Context context) {
        class SavePlanet extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                for (Planet planet : planets) {
                    Planet newPlanet = new Planet();
                    newPlanet.setSearchId(planet.getSearchId());
                    newPlanet.setName(planet.getName());
                    newPlanet.setIsPlanet(planet.isPlanet());
                    newPlanet.setImage(planet.getImage());

                    DatabaseClient.
                            getInstance(context.getApplicationContext()).
                            getAppDatabase().
                            planetDao().
                            insert(newPlanet);
                }
                return null;
            }
        }

        SavePlanet savePlanet = new SavePlanet();
        savePlanet.execute();
    }
}
