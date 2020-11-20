package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YoutubeSearchVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_search_video);
    }

    public void loadYoutubeTutorialActivity(View view) {
        Intent intent = new Intent(this, YoutubeTutorialActivity.class);
        startActivity(intent);
    }
}