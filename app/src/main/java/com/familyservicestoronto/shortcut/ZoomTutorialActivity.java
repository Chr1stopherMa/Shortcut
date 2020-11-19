package com.familyservicestoronto.shortcut;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class ZoomTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_tutorial);
    }

    public void onClickGoToSignUpTut(View view) {
        Intent intentSignUp = new Intent(this, ZoomSignUpActivity.class);
        startActivity(intentSignUp);
    }

    public void onClickGoToSignInTut(View view) {
        Intent intentSignIn = new Intent(this, ZoomSignInActivity.class);
        startActivity(intentSignIn);
    }

    public void onClickGoToJoinMeetingTut(View view) {
        Intent intentJoinMeeting = new Intent(this, ZoomJoinMeetingActivity.class);
        startActivity(intentJoinMeeting);
    }

    public void onClickGoToRunMeetingTut(View view) {
        Intent intentRunMeeting = new Intent(this, ZoomRunMeetingActivity.class);
        startActivity(intentRunMeeting);
    }

    public void onClickGoToHome(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }
}