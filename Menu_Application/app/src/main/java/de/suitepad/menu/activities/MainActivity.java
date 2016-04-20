package de.suitepad.menu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import de.suitepad.menu.R;
import de.suitepad.menu.fragments.AllFragment;
import de.suitepad.menu.fragments.MenuItemsFragment;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private NavigationView navigationView1;
    private NavigationView navigationView2;
    private boolean backPressedOnce = false;
    private AllFragment allFragment;
    private MenuItemsFragment appetizerFragment;
    private MenuItemsFragment mainCourseFragment;
    private MenuItemsFragment drinkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {

        initToolBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();

        navigationView1 = (NavigationView) findViewById(R.id.nav_view1);
        navigationView1.setNavigationItemSelectedListener(this);
        navigationView1.getMenu().getItem(0).setChecked(true);
        navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);
        setToolBarTitle(R.string.all);
        if (allFragment == null) {
            allFragment = new AllFragment();
        }
        replaceFragment(allFragment);
    }

    // handle on back press to be pressed twice
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (backPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.backPressedOnce = true;
            Toast.makeText(this, R.string.close_message, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressedOnce = false;

                }
            }, 2000);


        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            setToolBarTitle(R.string.all);
            if (allFragment == null) {
                allFragment = new AllFragment();
            }
            replaceFragment(allFragment);
        } else if (id == R.id.nav_appetizer) {
            setToolBarTitle(R.string.appetizer);
            if (appetizerFragment == null) {
                appetizerFragment = MenuItemsFragment.newInstance(1);
            }
            replaceFragment(appetizerFragment);
        } else if (id == R.id.nav_main_course) {
            setToolBarTitle(R.string.main_course);
            if (mainCourseFragment == null) {
                mainCourseFragment = MenuItemsFragment.newInstance(2);
            }
            replaceFragment(mainCourseFragment);
        } else if (id == R.id.nav_drink) {
            setToolBarTitle(R.string.drink);
            if (drinkFragment == null) {
                drinkFragment = MenuItemsFragment.newInstance(3);
            }
            replaceFragment(drinkFragment);
        } else if (id == R.id.nav_contact_us) {
            startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // replace fragment in main activity
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }
}
