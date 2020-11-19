package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FacebookTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_tutorial);
    }

    public void onClickGoToFacebook(View view) {
        try {
            Intent intentOpenFacebook = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            startActivity(intentOpenFacebook);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "https://play.google.com/store/apps/details?id=com.facebook.katana"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        }
    }

    public void onClickGoToAddFriendTut(View view) {
        Intent intentAddFriend = new Intent(this, FacebookAddFriendActivity.class);
        startActivity(intentAddFriend);
    }

    public void onClickGoToUploadTut(View view) {
        Intent intentAddFriend = new Intent(this, FacebookUploadActivity.class);
        startActivity(intentAddFriend);
    }

    public void onClickGoToEditProfileTut(View view) {
        Intent intentAddFriend = new Intent(this, FacebookEditProfileActivity.class);
        startActivity(intentAddFriend);
    }

    public void onClickGoToHome(View view) {
        Intent intentAddFriend = new Intent(this, HomeActivity.class);
        startActivity(intentAddFriend);
    }
}