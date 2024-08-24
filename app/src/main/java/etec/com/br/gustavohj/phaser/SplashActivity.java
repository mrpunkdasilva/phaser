package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * The SplashActivity class represents the splash screen of the application.
 * It displays the app's splash screen for a short duration before transitioning to the MainActivity.
 */
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * Hides the app's action bar and starts the MainActivity after a 2-second delay.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(i);
            finish();
        }, 2000);
    }
}