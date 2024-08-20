package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);

        /*
        * Manipulador para criar um delay dentro da Activity
        * - O Handler é Instanciado e usa-se o método postDelayed que deve ser passado o Runnable
        * - e dentro dele deve ser pasado uma function que vai conter o que será executado depois do dealy
        * - e o segundo argumento é o delay em millisegundos
        * */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}