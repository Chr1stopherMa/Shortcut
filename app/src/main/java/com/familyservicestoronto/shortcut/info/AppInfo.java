package com.familyservicestoronto.shortcut.info;


import android.content.Context;

import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * An object that contains information about the app.
 **/
public class AppInfo {

    public String appName;
    public String appPackage;
    public String appIconPath;
    public String uri;
    public Class<?> tutorialActivity;
    public JSONArray tutorials;

    // name of the JSON file containing app information
    private final static String appJSON = "apps.json";

    // the current application context
    private final Context context;

    public AppInfo(Context context, ExternalApp app) {
        this.context = context;
        try {
            JSONObject appData = getAppJSON(app);

            appName = appData.getString("name");
            appPackage = appData.getString("package");
            appIconPath = appData.getString("icon");
            uri = appData.getString("uri");
            tutorialActivity = Class.forName(appData.getString("tutorialActivity"));

            tutorials = appData.getJSONArray("tutorials");

        } catch (AppNotFoundException | JSONException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the JSON file containing the app data and return the JSON object
     * corresponding to the app.
     * @param app The name of the app.
     * @return A JSONObject containing information about the app.
     * @throws AppNotFoundException The app cannot be found in the JSON file.
     */
    private JSONObject getAppJSON(ExternalApp app) throws AppNotFoundException {
        try {
            JSONObject json = getJSON(appJSON, context);
            return json.getJSONObject(app.toString());
        } catch (JSONException e) {
            throw new AppNotFoundException(app.toString());
        }
    }

    public static JSONObject getJSON(String path, Context context) {
        try {
            InputStream stream = context.getAssets().open(path);
            int size = stream.available();
            byte[] buf = new byte[size];

            stream.read(buf);
            stream.close();

            String jsonString = new String(buf, "UTF-8");
            return new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    /**
     * Returns the drawable ID corresponding to the icon.
     * @return An int representing the drawable ID.
     */
    public int getDrawableID() {
        return context.getResources().getIdentifier(appIconPath,
                "drawable",
                context.getPackageName());
    }

    public ArrayList<CharSequence> getTutorialNames() {
        ArrayList<CharSequence> tutorialNames = new ArrayList<>();

        for (int i=0; i < tutorials.length(); i++) {
            try {
                tutorialNames.add(i, tutorials.getJSONObject(i).getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tutorialNames;
    }

    public ArrayList<CharSequence> getTutorialInstructions(int index) {
        ArrayList<CharSequence> instruct = new ArrayList<>();

        try {
            JSONArray instructions = tutorials.getJSONObject(index).getJSONArray("instructions");
            for (int i=0; i < instructions.length(); i++) {
                instruct.add(i, instructions.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return instruct;
    }

    public ArrayList<CharSequence> getTutorialImages(int index) {
        ArrayList<CharSequence> images = new ArrayList<>();

        try {
            JSONArray instructions = tutorials.getJSONObject(index).getJSONArray("images");
            for (int i=0; i < instructions.length(); i++) {
                images.add(i, instructions.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return images;
    }
}
