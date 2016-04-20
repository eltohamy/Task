package de.suitepad.support.provider;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.suitepad.support.models.MenuItem;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class DatabaseManager {

    private static DatabaseManager mInstance;
    private static DatabaseHelper mHelper;
    private static List<Class<?>> tables;

    public static DatabaseManager getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new DatabaseManager(context.getApplicationContext());
        }

        return mInstance;
    }

    private DatabaseManager(Context context) {
        tables = new ArrayList<Class<?>>();
        tables.add(MenuItem.class);
        mHelper = new DatabaseHelper(context, tables);
    }

    public DatabaseHelper getDatabaseHelper() {
        return mHelper;
    }

}
