package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;

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

import com.familyservicestoronto.shortcut.info.AppInfo;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;
import com.familyservicestoronto.shortcut.info.UserInfo;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;

import com.familyservicestoronto.shortcut.SwitchLanguage.LanguageSwitchUtil;
import com.familyservicestoronto.shortcut.SwitchLanguage.Languages;


import java.util.ArrayList;

/**
 * Defines the activity of the Home Page.
 *
 * The page follows a grid-like structure with 2 columns and 3 rows.
 * Each cell consists of an image and the name of the corresponding app.
 **/

public class HomeActivity extends AppCompatActivity {

    private ArrayList<ExternalApp> appNames;

    private LinearLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ConstraintLayout constraintLayout = findViewById(R.id.innerConstraint);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(10.0f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );

        appNames = UserInfo.getUserApps(this);

        mainLayout.setLayoutParams(params);
        addAppsToLayout();
      
        switchLanguageButton();

        // addLangButton();

        constraintLayout.addView(mainLayout);
    }

    private void addAppsToLayout() {
        for (int i=0; i < appNames.size(); i += 2) {
            addAppRow(i);
        }
        if (appNames.size() != 6 && appNames.size() % 2 == 0) {
            addAppRow(appNames.size());
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

        for (int i=index; i < Math.min(index+2, appNames.size()); i++) {
            AppInfo appInfo = new AppInfo(this, appNames.get(i));
            TextView appLabel = createAppLabel(appInfo.appName);
            rowLayout.addView(appLabel);

            ImageView appIcon = createAppIcon(appInfo);
            imageRow.addView(appIcon);
        }

        if ((index + 1 == appNames.size() && appNames.size() < 6) || index == appNames.size()) {
            TextView textView = addAppText();
            ImageView imageView = addAppButton();

            rowLayout.addView(textView);
            imageRow.addView(imageView);
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


    private ImageView addAppButton() {
        ImageView addApp = new ImageView(this);
        LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available height
                1.0f                            // fill only one column
        );
        addApp.setLayoutParams(imageParam);

        addApp.setImageResource(AppInfo.getAddAppImage(this));

        addApp.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAppMenu.class);
            startActivity(intent);
        });
        return addApp;
    }

    private TextView addAppText() {
        TextView textView = new TextView(new ContextThemeWrapper(this, R.style.appLabel));

        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available height
                1.0f                            // fill only one column
        );
        textView.setLayoutParams(textParam);

        // center text horizontally and vertically
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        textView.setText(R.string.add_app);
        return textView;
    }


    /**
     * Creates a TextView object that displays the current language and can be clicked
     * to toggle the language.
     */
    @SuppressLint("SetTextI18n")
    private void switchLanguageButton() {
        TextView textView = new TextView(this);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                0,
                1.0f                            // fill bottom row
        );
        textView.setLayoutParams(textParams);


        String text = getResources().getString(R.string.language);
        textView.setText(text + " " + UserInfo.getLanguage(this));

        textView.setGravity(Gravity.END | Gravity.BOTTOM);

        textView.setOnClickListener(v -> {
            switchLanguage();
        });

        mainLayout.addView(textView);
    }

    /**
     * Switches the language of the app and refreshes the home activity.
     * Called when the switch language button is pressed.
     */
    private void switchLanguage() {
        UserInfo.updateLanguage(this);
        Intent intent = new Intent(this, HomeActivity.class);
        finish();
        startActivity(intent);
    }

}