package com.familyservicestoronto.shortcut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familyservicestoronto.shortcut.AppInfo.AppInfo;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;


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

        ConstraintLayout constraintLayout = findViewById(R.id.innerConstraint);
        mainLayout = new LinearLayout(getApplicationContext());
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(9.0f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mainLayout.setLayoutParams(params);
        addAppsToLayout();
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

            ImageView appIcon = createAppIcon(appInfo.getDrawableID(), appInfo.tutorialActivity);
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
     * @param drawableID The app's drawable resource identifier
     * @param tutorialActivity The activity to load when the icon
     *                         is pressed
     * @return An ImageView containing the App's icon
     */
    private ImageView createAppIcon(int drawableID, Class<?> tutorialActivity) {
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available width
                LinearLayout.LayoutParams.MATCH_PARENT, // fill available height
                1.0f                            // fill only one column
        );
        imageView.setLayoutParams(imageParam);

        // set app icon
        imageView.setImageResource(drawableID);

        // add listener to redirect to tutorial page
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, tutorialActivity);
            startActivity(intent);

            // "pressed" animation
            imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.app_press));
        });

        return imageView;
    }
}