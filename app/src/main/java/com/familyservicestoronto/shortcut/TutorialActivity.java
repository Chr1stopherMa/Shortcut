package com.familyservicestoronto.shortcut;

import android.graphics.drawable.BitmapDrawable;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.familyservicestoronto.shortcut.SwitchLanguage.LanguageSwitchUtil;
import com.familyservicestoronto.shortcut.SwitchLanguage.Languages;

import java.util.ArrayList;

/**
 * Defines the activity for a tutorial.
 *
 * Each tutorial consists of a title at the top, a scroll view in the middle
 * containing the instructions and images, and a back button at the bottom.
 **/

public class TutorialActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private RelativeLayout relativeLayout;
    private Bundle bundle;

    private int prevID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_page);
        ConstraintLayout constraintLayout = findViewById(R.id.tutorialConstraintLayout);

        bundle = getIntent().getExtras();

        // Create vertical linear layout that expands to size of screen
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setWeightSum(9.0f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mainLayout.setLayoutParams(params);

        addTitle();
        addSteps();
        addBackButton();
        constraintLayout.addView(mainLayout);
    }

    /**
     * Creates a TextView widget which represents the title of the tutorial
     */
    private void addTitle() {
        TextView title = new TextView(new ContextThemeWrapper(this, R.style.TutorialTitle));
        String titleResource = bundle.getCharSequence("title").toString();

        int titleID = getResources().getIdentifier(titleResource, "string", this.getPackageName());
        title.setText(titleID);

        // center text horizontally and vertically
        title.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        // set layout parameters
        LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        title.setLayoutParams(titleParam);
        mainLayout.addView(title);
    }

    /**
     * Creates a ScrollView widget that contains the tutorial instructions.
     */
    private void addSteps() {
        ScrollView scrollView = new ScrollView(this);
        relativeLayout = new RelativeLayout(this);

        // scrollLayout.setOrientation(LinearLayout.VERTICAL);

        // set layout parameters
        LinearLayout.LayoutParams scrollViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 7.0f
        );
        LinearLayout.LayoutParams relLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        relativeLayout.setLayoutParams(relLayoutParams);
        scrollView.setLayoutParams(scrollViewParams);

        ArrayList<CharSequence> instructions = bundle.getCharSequenceArrayList("instructions");
        ArrayList<CharSequence> imagePaths = bundle.getCharSequenceArrayList("images");

        // add instructions
        for (int i=0; i < instructions.size(); i++) {
            addInstruction(instructions.get(i).toString(), imagePaths.get(i).toString());
        }

        scrollView.addView(relativeLayout);
        mainLayout.addView(scrollView);
    }


    private int generateID() {
        prevID += 10;
        return prevID;
    }

    private void addInstruction(String instruct, String imagePath) {
        TextView instruction = new TextView(new ContextThemeWrapper(this, R.style.TutorialText));

        // set layout parameters
        RelativeLayout.LayoutParams instructParams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        if (prevID != 0) {
            instructParams.addRule(RelativeLayout.BELOW, prevID);
        }
        instruction.setId(generateID());
        instruction.setLayoutParams(instructParams);

        int textID = getResources().getIdentifier(instruct, "string", this.getPackageName());
        instruction.setText(textID);

        relativeLayout.addView(instruction);

        if (!imagePath.equals("null")) {
            ImageView image = new ImageView(this);

            // set image
            int drawable = this.getResources().getIdentifier(imagePath,
                    "drawable", this.getPackageName());
            image.setImageResource(drawable);

            BitmapDrawable bd;
            bd = (BitmapDrawable) this.getResources().getDrawable(drawable);
            int height = bd.getBitmap().getHeight();

            // set layout parameters
            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    (int) (height * this.getResources().getDisplayMetrics().density * 7/10)
            );
            imageParams.addRule(RelativeLayout.BELOW, prevID);
            image.setId(generateID());
            image.setLayoutParams(imageParams);

            relativeLayout.addView(image);
        }

    }

    private void addBackButton() {
        Button backButton = new Button(this);
        backButton.setTextAppearance(this, R.style.AppBtn);
        backButton.setBackgroundResource(R.drawable.button);

        // set layout parameters
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        buttonParams.setMargins(10, 10, 10, 10);
        backButton.setLayoutParams(buttonParams);

        backButton.setText(R.string.back);

        backButton.setOnClickListener(v -> {
            backButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press));
            finish();
        });

        mainLayout.addView(backButton);
    }
}
