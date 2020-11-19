package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WhatsappProfileActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_profile);
    }

    public void onClickGoToWhatsappTut(View view) {
        Intent intent = new Intent(this, WhatsappTutActivity.class);
        startActivity(intent);
    }
}