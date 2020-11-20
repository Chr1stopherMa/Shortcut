package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // test opening Facebook
        try {
            ActivitySwitchUtil.openApp(getApplicationContext(), ExternalApp.FACEBOOK);
        } catch (AppNotFoundException e) {
            e.printStackTrace();
        }

    }
}