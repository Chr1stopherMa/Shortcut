package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView whatsapp = findViewById(R.id.WhatsappIcon);
        whatsapp.setOnClickListener(HomeActivity.this::loadWhatsappTutorialActivity);

        ImageView goToGoogle = findViewById(R.id.GoogleIcon);
        goToGoogle.setOnClickListener(HomeActivity.this::loadGoogleSearchActivity);

        ImageView goToZoom = findViewById(R.id.ZoomIcon);
        goToZoom.setOnClickListener(HomeActivity.this::loadZoomTutorialActivity);

        ImageView goToFacebook = findViewById(R.id.FacebookIcon);
        goToFacebook.setOnClickListener(HomeActivity.this::loadFacebookTutorialActivity);
    }

    public void loadWhatsappTutorialActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, WhatsappTutActivity.class);
        startActivity(intent);
    }

    public void loadGoogleSearchActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, GoogleSearchActivity.class);
        startActivity(intent);
    }

    public void loadZoomTutorialActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, ZoomTutorialActivity.class);
        startActivity(intent);
    }

    public void loadFacebookTutorialActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, FacebookTutorialActivity.class);
        startActivity(intent);
    }
}