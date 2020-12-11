package com.familyservicestoronto.shortcut;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.familyservicestoronto.shortcut.SwitchApp.ActivitySwitchUtil;
import com.familyservicestoronto.shortcut.SwitchApp.ExternalApp;
//import com.familyservicestoronto.shortcut.SwitchLanguage.LanguageSwitchUtil;
//import com.familyservicestoronto.shortcut.SwitchLanguage.Languages;

import java.util.ArrayList;

/**
 * Defines the activity for a tutorial page consisting of buttons for tutorials.
 *
 * Each page contains a list of buttons leading to the specified tutorial.
 **/

public class TutorialPageActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Bundle bundle;

    private int btnStyle;
    private int btnBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mainLayout = findViewById(R.id.tutorialLinearLayout);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(9.0f);

        btnStyle = R.style.AppBtn;
        btnBackground = R.drawable.button;

        bundle = getIntent().getExtras();
        String title = addTitle();
        addButtons(title);
        addBackButton(title);
    }

    private String addTitle() {
        TextView title = new TextView(new ContextThemeWrapper(this, R.style.AppTitle));

        String titleText = bundle.getCharSequence("name").toString();
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
            button.setTextAppearance(btnStyle);
            button.setBackgroundResource(btnBackground);

            int textID = getResources().getIdentifier(buttonTexts.get(i).toString(),
                    "string", this.getPackageName());
            button.setText(textID);
            Log.d("LangTutorialPageAddBtn", button.getText().toString());

            // set layout parameters
            LinearLayout.LayoutParams buttonParam = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
            );
            buttonParam.setMargins(5, 15, 5, 15);

            int finalI = i;
            button.setOnClickListener(v -> {
                button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press));
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
        buttonParams.setMargins(10, 50, 10, 3);

        // add back button
        Button backButton = new Button(this);
        backButton.setTextAppearance(btnStyle);
        backButton.setBackgroundResource(btnBackground);
        backButton.setLayoutParams(buttonParams);
        backButton.setText(R.string.back);

        backButton.setOnClickListener(v -> {
            backButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press));
            finish();
        });
        buttonLayout.addView(backButton);

        // add redirect button
        Button redirect = new Button(this);
        redirect.setTextAppearance(this, btnStyle);
        redirect.setBackgroundResource(btnBackground);
        redirect.setLayoutParams(buttonParams);
        String text = getResources().getString(R.string.go_to);
        redirect.setText(text + " " + title);

        redirect.setOnClickListener(v -> {
            redirect.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press));
            ActivitySwitchUtil.openApp(this, ExternalApp.valueOf(title.toUpperCase()));
        });

        buttonLayout.addView(redirect);

        mainLayout.addView(buttonLayout);
    }
}
