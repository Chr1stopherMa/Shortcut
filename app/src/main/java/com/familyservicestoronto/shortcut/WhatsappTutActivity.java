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
        Intent intentContact = new Intent(this, WhatsappNewContactActivity.class);
        startActivity(intentContact);
    }

    public void onClickPhoto(View view) {
        Intent intentPhoto = new Intent(this, WhatsappUploadActivity.class);
        startActivity(intentPhoto);
    }

    public void onClickProfile(View view) {
        Intent intentPhoto = new Intent(this, WhatsappProfileActivity.class);
        startActivity(intentPhoto);
    }

    public void onClickBack(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }

    public void onClickApp(View view) {

    }
}
