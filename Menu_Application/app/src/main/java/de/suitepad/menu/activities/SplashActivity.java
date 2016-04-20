package de.suitepad.menu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import de.suitepad.menu.R;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class SplashActivity extends BaseActivity {
    private static final long SPLASH_TIME = 2000;
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        openMainActivity();
    }

    // finish then open main activity after SPLASH_TIME
    protected void openMainActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, SPLASH_TIME);
    }
}
