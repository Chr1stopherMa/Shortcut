package com.familyservicestoronto.shortcut;

import android.content.Context;
import android.content.Intent;

/**
 * A utility class that can be used to start external activities.
**/

public final class ActivitySwitchUtil {
    /**
     * Switches the current activity with the main activity of the application
     * given by the package name.
     * @param context The current application context.
     * @param packageName The name of the package.
     * @throws AppNotFoundException The app is not installed.
     **/
    public static void switchActivity(Context context, String packageName) throws AppNotFoundException {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null) {
            throw new AppNotFoundException(packageName);
        }

        context.startActivity(launchIntent);
    }
}
