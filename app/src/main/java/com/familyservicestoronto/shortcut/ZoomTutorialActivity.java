package com.familyservicestoronto.shortcut;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class ZoomTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_tutorial);
        Button goToZoom = findViewById(R.id.GoToZoomButton);
        goToZoom.setOnClickListener(this::openZoom);
    }

    public void openZoom(View view) {
        try {
            Intent i = getPackageManager().getLaunchIntentForPackage("us.zoom.videomeetings");
            startActivity(i);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "https://play.google.com/store/apps/details?id=us.zoom.videomeetings"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        }
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