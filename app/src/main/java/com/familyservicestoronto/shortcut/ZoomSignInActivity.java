package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ZoomSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_signin);
    }

    public void onClickGoToZoomTut(View view) {
        Intent intent = new Intent(this, ZoomTutorialActivity.class);
        startActivity(intent);
    }
}