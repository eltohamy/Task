package de.suitepad.menu.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.Locale;

import de.suitepad.menu.R;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String EN = "en";
    protected Toolbar toolbar;
    protected TextView toolbarTitle;

    //set application locale
    public static void setLocale(String localeName, Context context) {
        Locale locale = new Locale(localeName);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();
        setLocale(EN, BaseActivity.this);
    }

    // set status bar color
    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(
                    R.color.colorPrimaryDark));
        }
    }

    // init tool bar
    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);

    }

    // set toolbar title
    protected void setToolBarTitle(int titleResource) {
        toolbarTitle.setText(titleResource);
    }

    // set toolbar title
    protected void setToolBarTitle(String title) {
        toolbarTitle.setText(title);
    }

    // hide soft input
    protected void hideSoftInput() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
