package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_youtube_search_video);
    }
}