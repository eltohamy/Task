package de.suitepad.support.provider;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import de.suitepad.support.models.MenuItem;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "menuitems.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_PATH;
    private List<Class<?>> tables;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_PATH = context.getFilesDir().getParentFile().getPath()
                + "/databases/";
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            // If database did not exist, try copying existing database from
            // assets folder.
            copyDatabase(context);
        }
        tables = new ArrayList<>();
        tables.add(MenuItem.class);
    }

    public DatabaseHelper(Context context, List<Class<?>> mtables) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_PATH = context.getFilesDir().getParentFile().getPath()
                + "/databases/";
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            // If database did not exist, try copying existing database from
            // assets folder.
            copyDatabase(context);
        }
        tables = mtables;
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            for (Class<?> key : tables) {
                TableUtils.createTableIfNotExists(connectionSource, key);
            }
        } catch (java.sql.SQLException e) {
            Log.e(TAG, "Can't create table", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 1:
                    // this is where we want to execute all further update queries
                    break;
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(TAG, "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }

    /*
         * Check whether or not database exist
         */
    private boolean checkDatabase() {
        boolean checkdb = false;
        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();
        Log.i(DatabaseHelper.class.getName(), "DB Exist : " + checkdb);
        return checkdb;
    }

    // copy data base from assets
    private void copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            Log.i(DatabaseHelper.class.getName(), "DB Path : "
                    + outFileName);
            File mFolder = new File(DATABASE_PATH);
            if (!mFolder.exists()) {
                mFolder.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(outFileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
