package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FacebookTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_tutorial);
    }

    public void onClickGoToAddFriendTut(View view) {
        Intent intentAddFriend = new Intent(this, FacebookAddFriendActivity.class);
        startActivity(intentAddFriend);
    }

    public void onClickGoToUploadTut(View view) {
        Intent intentAddFriend = new Intent(this, FacebookUploadActivity.class);
        startActivity(intentAddFriend);
    }

    public void onClickGoToHome(View view) {
        Intent intentAddFriend = new Intent(this, HomeActivity.class);
        startActivity(intentAddFriend);
    }
}