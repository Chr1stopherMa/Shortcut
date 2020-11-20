package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YoutubeSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_sign_in);

    }

    public void loadYoutubeTutorialActivity(View view) {
        Intent intent = new Intent(this, YoutubeTutorialActivity.class);
        startActivity(intent);
    }
}