package com.familyservicestoronto.shortcut;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WhatsappTutActivity extends AppCompatActivity {
Button bopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_tutorial);
        bopen=findViewById(R.id.goToWhatsapp);
        bopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                    startActivity(i);
                } catch (Exception e) {
                    showToast("Whatsapp App is Not installed.");
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(WhatsappTutActivity.this, text, Toast.LENGTH_SHORT).show();
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
