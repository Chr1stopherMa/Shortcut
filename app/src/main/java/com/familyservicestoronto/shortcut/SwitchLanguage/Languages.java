package com.familyservicestoronto.shortcut.SwitchLanguage;


import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.Log;

public class Languages {
    /**
     * Languages that can be used.
     */
    public enum Language {
        ENGLISH,
        ESPAGNOL
    }

    //Language code of the current language
    public static String currentLanguage;

    public static void setLanguage(String languageCode) {
        currentLanguage = languageCode;
    }

    public static void swapLanguage(){
        String lang = currentLanguage.substring(0,2);
        switch(lang){
            case "es":
                currentLanguage = "en";
                break;
            case "en":
                currentLanguage = "es";
                break;
            default:
                currentLanguage = "error";
        }
        Log.d("LangSwapLanguage",currentLanguage);
    }

    public static LocaleList getMainLanguage(Activity activity){
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();

        Log.d("LangGetMainLanguage", String.valueOf(config.getLocales()));
        return(config.getLocales());
    }

}