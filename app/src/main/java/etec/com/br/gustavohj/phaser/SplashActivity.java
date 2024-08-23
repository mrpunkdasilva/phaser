package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * The SplashActivity class represents the splash screen activity of the application.
 * It displays a splash screen and then transitions to the MainActivity after a brief delay.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * Initializes the splash screen and starts the MainActivity after a 1-second delay.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);

        /**
         * Creates a delay using a Handler.
         * The Handler is instantiated and the postDelayed method is used, which takes a Runnable
         * and the delay in milliseconds.
         * The Runnable contains the code to be executed after the delay.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}