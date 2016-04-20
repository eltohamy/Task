package de.suitepad.menu.activities;

import android.os.Bundle;
import android.view.MenuItem;

import de.suitepad.menu.R;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class ContactUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initUI();
    }

    public void initUI() {
        initToolBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setToolBarTitle(R.string.contact_us);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}