package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.ViewGroup;

import com.familyservicestoronto.shortcut.AppInfo.AppInfo;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;
import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;

import com.familyservicestoronto.shortcut.SwitchLanguage.LanguageSwitchUtil;
import com.familyservicestoronto.shortcut.SwitchLanguage.Languages;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines the activity of the Home Page.
 *
 * The page follows a grid-like structure with 2 columns and 3 rows.
 * Each cell consists of an image and the name of the corresponding app.
 **/

public class HomeActivity extends AppCompatActivity {

    private final ArrayList<ExternalApp> appNames = new ArrayList<>(
            Arrays.asList(ExternalApp.FACEBOOK, ExternalApp.ZOOM,
                    ExternalApp.YOUTUBE, ExternalApp.WHATSAPP,
                    ExternalApp.GMAIL, ExternalApp.GOOGLE)
    );

    private LinearLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        Languages.setLanguage(Languages.getMainLanguage(this).get(0).toString());

        ConstraintLayout constraintLayout = findViewById(R.id.innerConstraint);
        mainLayout = new LinearLayout(getApplicationContext());
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(10.0f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mainLayout.setLayoutParams(params);
        addAppsToLayout();
        addLangButton();
        constraintLayout.addView(mainLayout);
    }

    private void addAppsToLayout() {
        for (int i=0; i < appNames.size(); i += 2) {
            addAppRow(i);
        }
    }

    private void addAppRow(int index) {
        LinearLayout rowLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        rowLayout.setWeightSum(2.0f);
        LinearLayout imageRow = new LinearLayout(this);
        imageRow.setWeightSum(2.0f);
        imageRow.setOrientation(LinearLayout.HORIZONTAL);
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i=index; i < index+2; i++) {
            AppInfo appInfo = new AppInfo(this, appNames.get(i));
            TextView appLabel = createAppLabel(appInfo.appName);
            rowLayout.addView(appLabel);

            ImageView appIcon = createAppIcon(appInfo);
            imageRow.addView(appIcon);

        }
        LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 2.0f
        );
        rowLayout.setLayoutParams(params);
        imageRow.setLayoutParams(imageParam);
        mainLayout.addView(imageRow);
        mainLayout.addView(rowLayout);

    }

    /**
     * Creates a TextView widget corresponding to the app label displayed
     * under the icon.
     * @param appName The app's name
     * @return A TextView containing the app's name
     */
    private TextView createAppLabel(String appName) {
        TextView textView = new TextView(new ContextThemeWrapper(this, R.style.appLabel));

        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available height
                1.0f                            // fill only one column
        );
        textView.setLayoutParams(textParam);

        // center text horizontally and vertically
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        // set text
        textView.setText(appName);
        return textView;
    }


    /**
     * Creates a ImageView widget displaying the App's icon
     * under the icon
     * @param appInfo An object containing information about the app
     * @return An ImageView containing the App's icon
     */
    private ImageView createAppIcon(AppInfo appInfo) {
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available height
                1.0f                            // fill only one column
        );
        imageView.setLayoutParams(imageParam);

        // set app icon
        imageView.setImageResource(appInfo.getDrawableID());

        // add listener to redirect to tutorial page
        imageView.setOnClickListener(v -> {
            // "pressed" animation
            imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.app_press));

            Intent intent = new Intent(HomeActivity.this, TutorialPageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putCharSequence("name", appInfo.appName);
            bundle.putCharSequenceArrayList("tutorials", appInfo.getTutorialNames());

            for (int i=0; i < appInfo.getTutorialNames().size(); i++) {
                bundle.putCharSequenceArrayList("instructions" + i, appInfo.getTutorialInstructions(i));
                bundle.putCharSequenceArrayList("images" + i, appInfo.getTutorialImages(i));
            }
            intent.putExtras(bundle);
            startActivity(intent);
        });

        return imageView;
    }

    /**
     * Adds a toggleButton for language at the bottom of the screen
     */
    private void addLangButton() {
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setWeightSum(1.0f);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        buttonLayout.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f
        );
        buttonParams.setMargins(10, 0, 10, 0);

        Button langButton = new Button(this);
        langButton.setLayoutParams(buttonParams);

        langButton.setText(R.string.lang);

        // add listener to redirect to change language and refresh this page
        langButton.setOnClickListener(v -> {
            // "pressed" animation
            langButton.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.app_press));
            Languages.swapLanguage();

            LanguageSwitchUtil.setLocale((Activity) this, Languages.currentLanguage);
            //The page must be recreated if we want the text on this page to update.
            //HomeActivity.this.recreate();
            //recreate() has a nasty black flash, this does the same with a transition:
            finish();
            startActivity(getIntent());
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        buttonLayout.addView(langButton);
        mainLayout.addView(buttonLayout);
    }
}