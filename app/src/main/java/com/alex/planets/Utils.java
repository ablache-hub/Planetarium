package com.alex.planets;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    static String getJsonFromAssets(Context context, String jsonFile) {
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
}
