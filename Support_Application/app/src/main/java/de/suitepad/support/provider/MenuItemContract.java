package de.suitepad.support.provider;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class MenuItemContract {

    public static final String TABLE_NAME = "MenuItem";

    public static final String AUTHORITY = "de.suitepad.support";

    public static final String CONTENT_URI_PATH = "menuitems";

    public static final String MIMETYPE_TYPE = "menuitems";
    public static final String MIMETYPE_NAME = "de.suitepad.support.provider";

    public static final int CONTENT_URI_PATTERN_MANY = 1;
    public static final int CONTENT_URI_PATTERN_ONE = 2;

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String TYPE = "type";
}
