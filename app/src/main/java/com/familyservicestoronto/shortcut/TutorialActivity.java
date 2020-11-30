package com.familyservicestoronto.shortcut;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.ArrayList;

/**
 * Defines the activity for a tutorial.
 *
 * Each tutorial consists of a title at the top, a scroll view in the middle
 * containing the instructions and images, and a back button at the bottom.
 **/

public class TutorialActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private LinearLayout scrollLayout;
    private Bundle bundle;

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
        TextView title = new TextView(new ContextThemeWrapper(this, R.style.appLabel));
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
        scrollLayout = new LinearLayout(this);

        scrollLayout.setOrientation(LinearLayout.VERTICAL);

        // set layout parameters
        LinearLayout.LayoutParams scrollViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 7.0f
        );
        LinearLayout.LayoutParams scrollLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        scrollLayout.setLayoutParams(scrollLayoutParams);
        scrollView.setLayoutParams(scrollViewParams);

        ArrayList<CharSequence> instructions = bundle.getCharSequenceArrayList("instructions");
        ArrayList<CharSequence> imagePaths = bundle.getCharSequenceArrayList("images");

        // add instructions
        for (int i=0; i < instructions.size(); i++) {
            addInstruction(instructions.get(i).toString(), imagePaths.get(i).toString());
        }

        scrollView.addView(scrollLayout);
        mainLayout.addView(scrollView);
    }

    private void addInstruction(String instruct, String imagePath) {
        TextView instruction = new TextView(this);

        // set layout parameters
        LinearLayout.LayoutParams instructParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        instruction.setLayoutParams(instructParams);

        int textID = getResources().getIdentifier(instruct, "string", this.getPackageName());
        instruction.setText(textID);

        scrollLayout.addView(instruction);

        if (!imagePath.equals("null")) {
            ImageView image = new ImageView(this);
            int drawable = this.getResources().getIdentifier(imagePath,
                    "drawable", this.getPackageName());
            image.setLayoutParams(instructParams);
            image.setImageResource(drawable);
            scrollLayout.addView(image);
        }

    }

    private void addBackButton() {
        Button backButton = new Button(this);

        // set layout parameters
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f
        );
        backButton.setLayoutParams(buttonParams);

        backButton.setText(R.string.back);

        backButton.setOnClickListener(v -> {
            finish();
        });

        mainLayout.addView(backButton);
    }
}
