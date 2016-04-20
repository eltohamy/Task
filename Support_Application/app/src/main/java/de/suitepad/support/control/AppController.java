package de.suitepad.support.control;

import android.app.Application;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
//        DatabaseManager.getInstance(getApplicationContext());
    }
}
