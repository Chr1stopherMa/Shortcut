package com.familyservicestoronto.shortcut.SwitchApp;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * A utility class that can be used to start external activities.
 **/

public final class ActivitySwitchUtil {

    private final static String appJSON = "apps.json";

    /**
     * Opens an external app.
     * @param context The current application context.
     * @param app The name of the app to open.
     * @throws AppNotFoundException The package name cannot be found
     *                              or the app is not installed.
     */
    public static void openApp(Context context, ExternalApp app) throws AppNotFoundException {
        String packageName = getPackageName(context, app);
        switchActivity(context, packageName);
    }

    /**
     * Gets the package name of app from appJSON file.
     * @param context The current application context.
     * @param app The name of the app.
     * @return The package name corresponding to the app.
     * @throws AppNotFoundException App is not listed in the appJSON file.
     */
    private static String getPackageName(Context context, ExternalApp app) throws AppNotFoundException {
        String jsonString = null;
        try {
            InputStream stream = context.getAssets().open(appJSON);
            int size = stream.available();
            byte[] buf = new byte[size];
            stream.read(buf);
            stream.close();
            jsonString = new String(buf, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert jsonString != null;
        try {
            JSONObject json = new JSONObject(jsonString);
            return (String) json.get(app.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new AppNotFoundException(app.toString());
        }
    }

    /**
     * Switches the current activity with the main activity of the application
     * given by the package name.
     * @param context The current application context.
     * @param packageName The name of the package.
     * @throws AppNotFoundException The app is not installed.
     **/
    private static void switchActivity(Context context, String packageName) throws AppNotFoundException {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null) {
            throw new AppNotFoundException(packageName);
        }

        context.startActivity(launchIntent);
    }
}
