package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GmailAddAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_add_account);
    }

    public void onClickGoToGmailTut(View view) {
        Intent intent = new Intent(this, GmailTutorialActivity.class);
        startActivity(intent);
    }
}