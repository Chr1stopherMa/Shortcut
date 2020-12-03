package com.familyservicestoronto.shortcut.info;

import android.content.Context;


import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;
import com.familyservicestoronto.shortcut.language.Language;
import com.familyservicestoronto.shortcut.language.LanguageUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A utility class that can be used to read and update user settings.
 **/

public class UserInfo {

    private static final String JSONfile = "user.json";

    private static JSONObject getUserData(Context context) {
        // check if user already has personal settings
        // Source: https://medium.com/@nayantala259/android-how-to-read-and-write-parse-data
        // -from-json-file-226f821e957a
        try {
            File file = new File(context.getFilesDir(), JSONfile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            String jsonString = stringBuilder.toString();
            return new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            // use default settings
            return AppInfo.getJSON(JSONfile, context);
        }
    }

    private static void writeJSON(Context context, JSONObject json) {
        try {
            String jsonString = json.toString();
            File file = new File(context.getFilesDir(), JSONfile);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonString);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the current language setting of the app.
     * @param context The current application context.
     * @return The app's current language.
     */
    public static Language getLanguage(Context context) {
        JSONObject json = getUserData(context);
        try {
            Language language = Language.valueOf(json.getString("language"));
            LanguageUtil.switchLanguage(context, language);
            return language;
        } catch (JSONException e) {
            return Language.en;
        }
    }

    /**
     * Toggles the current language.
     */
    public static void updateLanguage(Context context) {
        JSONObject json = getUserData(context);
        try {
            if (getLanguage(context) == Language.en) {
                json.put("language", Language.es.toString());
            } else {
                json.put("language", Language.en.toString());
            }
            writeJSON(context, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the user's default apps to display on the screen.
     * @param context The current application context.
     * @return An ArrayList of the apps to display on the home screen.
     */
    public static ArrayList<ExternalApp> getUserApps(Context context) {
        JSONObject json = getUserData(context);
        ArrayList<ExternalApp> userApps = new ArrayList<>();

        try {
            JSONArray apps = json.getJSONArray("apps");
            for (int i=0; i < apps.length(); i++) {
                userApps.add(i, ExternalApp.valueOf(apps.getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userApps;
    }

    /**
     * Get every app that can be added by the user (i.e. not in their defaults)
     * @param context The current application context.
     * @return An ArrayList of the apps that can be added to the home screen.
     */
    public static ArrayList<ExternalApp> getAppsToAdd(Context context) {
        ArrayList<ExternalApp> currentApps = getUserApps(context);
        ArrayList<ExternalApp> appsToAdd = new ArrayList<>();

        for (ExternalApp app : ExternalApp.values()) {
            if (!currentApps.contains(app)) {
                appsToAdd.add(app);
            }
        }

        return appsToAdd;
    }

    /**
     * Add app to the users list of current apps.
     * @param context The current application context.
     * @param app The app to add.
     */
    public static void updateApps(Context context, ExternalApp app) {
        ArrayList<ExternalApp> currentApps = getUserApps(context);
        currentApps.add(app);

        JSONArray updatedApps = new JSONArray();
        for (ExternalApp app_ : currentApps) {
            updatedApps.put(app_.toString());
        }

        JSONObject json = getUserData(context);
        try {
            json.put("apps", updatedApps);
            writeJSON(context, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
