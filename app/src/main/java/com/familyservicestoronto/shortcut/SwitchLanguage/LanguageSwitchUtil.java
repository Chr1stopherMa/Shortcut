package com.familyservicestoronto.shortcut.SwitchLanguage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.familyservicestoronto.shortcut.SwitchLanguage.Languages;

import java.util.Locale;


/**
 * A utility class that can be used to switch the app's language.
 **/

public final class LanguageSwitchUtil {

    /**
     * Switches the current language locale for the given activity.
     * @param activity The current activity.
     * @param languageCode The language string for the desired language.
     * Code based on a 2020/03/26 update of this SO question:
     * https://stackoverflow.com/questions/2900023/change-app-language-programmatically-in-android
     **/
    public static void setLocale(Activity activity, String languageCode) {
        Languages.getMainLanguage(activity); //

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
