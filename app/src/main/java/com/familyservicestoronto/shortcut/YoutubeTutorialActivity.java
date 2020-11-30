package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.familyservicestoronto.shortcut.AppInfo.AppInfo;
import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class YoutubeTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_tutorial);

    }

    public void onClickBack(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }

    public void onClickSearch(View view) {
        AppInfo appInfo = new AppInfo(this, ExternalApp.YOUTUBE);
        try {
            JSONObject tutorial = appInfo.tutorials.getJSONObject(0);
            Intent intentHome = new Intent(this, TutorialActivity.class);
            Bundle bundle = new Bundle();
            bundle.putCharSequence("title", tutorial.getString("title"));
            ArrayList<CharSequence> instructions = new ArrayList<>();
            instructions.add(0, "1. This is the first step");
            instructions.add(1, "2. This is the second step");
            ArrayList<CharSequence> imagePaths = new ArrayList<>();
            imagePaths.add(0, "youtube_signed_out");
            imagePaths.add(1, "youtube_add_account");
            bundle.putCharSequenceArrayList("instructions", instructions);
            bundle.putCharSequenceArrayList("images", imagePaths);
            intentHome.putExtras(bundle);
            startActivity(intentHome);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onClickLogin(View view) {
        Intent intentHome = new Intent(this, YoutubeSignInActivity.class);
        startActivity(intentHome);
    }

    public void goToYoutube(View view) {
        try {
            ActivitySwitchUtil.openApp(this, ExternalApp.YOUTUBE);
        } catch (AppNotFoundException e) {

        }
    }
}