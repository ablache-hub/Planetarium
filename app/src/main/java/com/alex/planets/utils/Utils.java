package com.alex.planets.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.alex.planets.database.DatabaseClient;
import com.alex.planets.models.Planet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Utils {
    public static String getJsonToString(Context context, String jsonFile) {
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

    public static void savePlanet(List<Planet> planets, Context context) {
        class SavePlanet extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                for (Planet planet : planets) {
                    Planet newPlanet = new Planet();
                    newPlanet.setEnglishName(planet.getEnglishName());
                    newPlanet.setName(planet.getName());
                    newPlanet.setIsPlanet(planet.isPlanet());
                    newPlanet.setImage(planet.getImage());
                    newPlanet.setAroundPlanet(planet.getAroundPlanet());
                    newPlanet.setMoons(planet.getMoons());

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
