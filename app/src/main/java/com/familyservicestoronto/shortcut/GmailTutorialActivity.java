package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.net.Uri;

import java.io.StringWriter;
import java.io.PrintWriter;

public class GmailTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_tutorial);
        Button goToGmail = findViewById(R.id.GoToGmailButton);
        goToGmail.setOnClickListener(this::openGmail);
    }

    public void openGmail(View view) {
        try {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            startActivity(i);
        } catch (Exception e) {
            showToast("Gmail is not installed.");
        }
    }

    private void showToast(String text) {
        Toast.makeText(GmailTutorialActivity.this, text, Toast.LENGTH_SHORT).show();
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