package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GmailTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_tutorial);
    }

    public void onClickGoToAddAccountTut(View view) {
        Intent intentAddAccount = new Intent(this, GmailAddAccountActivity.class);
        startActivity(intentAddAccount);
    }

    public void onClickGoToOpenEmailTut(View view) {
        Intent intentOpenEmail = new Intent(this, GmailOpenEmailActivity.class);
        startActivity(intentOpenEmail);
    }

    public void onClickGoToComposeEmailTut(View view) {
        Intent intentComposeEmail = new Intent(this, GmailComposeEmailActivity.class);
        startActivity(intentComposeEmail);
    }

    public void onClickGoToHome(View view) {
        Intent intentGoHome = new Intent(this, HomeActivity.class);
        startActivity(intentGoHome);
    }
}