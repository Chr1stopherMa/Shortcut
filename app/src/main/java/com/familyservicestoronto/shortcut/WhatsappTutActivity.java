package com.familyservicestoronto.shortcut;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class WhatsappTutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_tutorial);
    }

    public void onClickNewContact(View view) {

    }

    public void onClickPhoto(View view) {

    }

    public void onClickProfile(View view) {

    }

    public void onClickBack(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }

    public void onClickApp(View view) {

    }
}
