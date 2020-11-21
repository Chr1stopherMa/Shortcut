package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;

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
        Intent intentHome = new Intent(this, YoutubeSearchVideoActivity.class);
        startActivity(intentHome);
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