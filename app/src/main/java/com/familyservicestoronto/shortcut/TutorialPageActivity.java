package com.familyservicestoronto.shortcut;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.AppNotFoundException;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;

import java.util.ArrayList;

/**
 * Defines the activity for a tutorial page consisting of buttons for tutorials.
 *
 * Each page contains a list of buttons leading to the specified tutorial.
 **/

public class TutorialPageActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ConstraintLayout constraintLayout = findViewById(R.id.tutorialConstraint);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(9.0f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mainLayout.setLayoutParams(params);

        bundle = getIntent().getExtras();
        String title = addTitle();
        addButtons(title);
        addBackButton(title);
        constraintLayout.addView(mainLayout);
    }

    private String addTitle() {
        TextView title = new TextView(new ContextThemeWrapper(this, R.style.appLabel));

        String titleText = bundle.getCharSequence("name").toString();
        // int titleID = getResources().getIdentifier(titleText, "string", this.getPackageName());
        title.setText(titleText);

        // center text horizontally and vertically
        title.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        // set layout parameters
        LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        title.setLayoutParams(titleParam);
        mainLayout.addView(title);

        return titleText;
    }

    private void addButtons(String title) {
        ArrayList<CharSequence> buttonTexts = bundle.getCharSequenceArrayList("tutorials");

        for (int i=0; i < buttonTexts.size(); i++) {
            Button button = new Button(this);
            button.setText(buttonTexts.get(i));

            // set layout parameters
            LinearLayout.LayoutParams buttonParam = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
            );

            int finalI = i;
            button.setOnClickListener(v -> {
                ArrayList<CharSequence> instructions = bundle.getCharSequenceArrayList("instructions" + finalI);
                ArrayList<CharSequence> images = bundle.getCharSequenceArrayList("images" + finalI);

                Intent intent = new Intent(this, TutorialActivity.class);
                Bundle bundle = new Bundle();

                bundle.putCharSequence("title", buttonTexts.get(finalI));
                bundle.putCharSequenceArrayList("instructions", instructions);
                bundle.putCharSequenceArrayList("images", images);

                intent.putExtras(bundle);
                startActivity(intent);
            });
            button.setLayoutParams(buttonParam);
            mainLayout.addView(button);
        }
    }

    @SuppressLint("SetTextI18n")
    private void addBackButton(String title) {
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setWeightSum(2.0f);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        buttonLayout.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
        );

        // add back button
        Button backButton = new Button(this);
        backButton.setLayoutParams(buttonParams);
        backButton.setText(R.string.back);

        backButton.setOnClickListener(v -> {
            finish();
        });
        buttonLayout.addView(backButton);

        // add redirect button
        Button redirect = new Button(this);
        redirect.setLayoutParams(buttonParams);
        String text = getResources().getString(R.string.go_to);
        redirect.setText(text + title);

        redirect.setOnClickListener(v -> {
            try {
                ActivitySwitchUtil.openApp(this, ExternalApp.valueOf(title.toUpperCase()));
            } catch (AppNotFoundException e) {
                e.printStackTrace();
            }
        });

        buttonLayout.addView(redirect);

        mainLayout.addView(buttonLayout);
    }
}
