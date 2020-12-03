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
import android.widget.ScrollView;
import android.widget.TextView;

import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;
import com.familyservicestoronto.shortcut.info.AppInfo;
import com.familyservicestoronto.shortcut.info.UserInfo;

import java.util.ArrayList;

public class AddAppMenu extends AppCompatActivity {

    private LinearLayout mainLayout;
    private ArrayList<ExternalApp> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app_menu);

        ConstraintLayout constraintLayout = findViewById(R.id.addAppConstraint);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(params);
        //constraintLayout.addView(scrollView);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(params);
        mainLayout.setWeightSum(9.0f);

        apps = UserInfo.getAppsToAdd(this);
        addAppsToLayout();

        constraintLayout.addView(mainLayout);
        //scrollView.addView(mainLayout);
    }

    private void addAppsToLayout() {
        for (int i=0; i < apps.size(); i += 2) {
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

        for (int i=index; i < Math.min(index+2, apps.size()); i++) {
            AppInfo appInfo = new AppInfo(this, apps.get(i));
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

            UserInfo.updateApps(this, appInfo.app);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        return imageView;
    }
}