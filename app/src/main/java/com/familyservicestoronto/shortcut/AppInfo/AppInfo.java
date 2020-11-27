package com.familyservicestoronto.shortcut.AppInfo;


import android.content.Context;

import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * An object that contains information about the app.
 **/
public class AppInfo {

    public String appName;
    public String appPackage;
    public String appIconPath;

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

        } catch (AppNotFoundException | JSONException e) {
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
        String jsonString = null;

        try {
            InputStream stream = context.getAssets().open(appJSON);
            int size = stream.available();
            byte[] buf = new byte[size];
            stream.read(buf);
            stream.close();
            jsonString = new String(buf, "UTF-8");
        } catch (IOException e) {
            throw new AppNotFoundException(app.toString());
        }

        try {
            JSONObject json = new JSONObject(jsonString);
            return json.getJSONObject(app.toString());
        } catch (JSONException e) {
            throw new AppNotFoundException(app.toString());
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
}
