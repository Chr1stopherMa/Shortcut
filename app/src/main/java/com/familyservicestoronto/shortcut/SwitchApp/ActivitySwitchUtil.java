package com.familyservicestoronto.shortcut.SwitchApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.familyservicestoronto.shortcut.AppInfo.AppInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * A utility class that can be used to start external activities.
 **/

public final class ActivitySwitchUtil {

    private static final String vending = "com.android.vending";

    /**
     * Opens an external app.
     * @param context The current application context.
     * @param app The name of the app to open.
     */
    public static void openApp(Context context, ExternalApp app) {
        String packageName = getPackageName(context, app);
        try {
            switchActivity(context, packageName);
        } catch (AppNotFoundException e) {
            // app is not installed on the user's phone
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    new AppInfo(context, app).uri));
            intent.setPackage(vending);
            context.startActivity(intent);
        }
    }

    /**
     * Gets the package name of app from appJSON file.
     * @param context The current application context.
     * @param app The name of the app.
     * @return The package name corresponding to the app.
     */
    private static String getPackageName(Context context, ExternalApp app) {
        return new AppInfo(context, app).appPackage;
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
