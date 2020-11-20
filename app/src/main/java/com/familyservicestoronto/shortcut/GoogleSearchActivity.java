package com.familyservicestoronto.shortcut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GoogleSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_tutorial);
        Button goToGoogle = findViewById(R.id.goToGoogle);
        goToGoogle.setOnClickListener(this::openGoogle);
    }

    public void openGoogle(View view) {
        // Intent googleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        // startActivity(googleIntent);
        try {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            startActivity(i);
        } catch (Exception e) {
            showToast("Google Chrome is not installed.");
        }
    }

    private void showToast(String text) {
        Toast.makeText(GoogleSearchActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public void onClickBack(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }
}
