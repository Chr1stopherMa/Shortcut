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
        //DOESN'T WORK:
        try {
            Intent mailClient = new Intent(Intent.ACTION_VIEW);
            mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
            startActivity(mailClient);
        } catch (Exception e) {
            showToast(e);
        }
    }

    private void showToast(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        //String sStackTrace = sw.toString(); // stack trace as a string
        Toast.makeText(GmailTutorialActivity.this, sw.toString(), Toast.LENGTH_LONG).show();
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