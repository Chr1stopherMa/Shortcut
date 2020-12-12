package com.familyservicestoronto.shortcut.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * A utility class that can be used to switch the app's language settings.
 **/

public class LanguageUtil {

    /**
     * Changes the language of the app.
     * @param context The current application context.
     * @param language The language to switch to.
     */
    public static Language switchLanguage(Context context, Language language) {
        Locale locale = new Locale(language.toString());
        Resources resources = context.getResources();

        Configuration config = resources.getConfiguration();
        config.locale = locale;

        resources.updateConfiguration(config, resources.getDisplayMetrics());

        return language;
    }
}
