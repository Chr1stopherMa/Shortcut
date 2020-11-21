package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FacebookAddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_add_friend);
    }

    public void onClickGoToFacebookTut(View view) {
        Intent intent = new Intent(this, FacebookTutorialActivity.class);
        startActivity(intent);
    }
}