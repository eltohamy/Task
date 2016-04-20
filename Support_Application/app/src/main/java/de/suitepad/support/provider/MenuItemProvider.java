package de.suitepad.support.provider;

import com.tojc.ormlite.android.OrmLiteSimpleContentProvider;
import com.tojc.ormlite.android.framework.MatcherController;
import com.tojc.ormlite.android.framework.MimeTypeVnd.SubType;

import de.suitepad.support.models.MenuItem;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class MenuItemProvider extends OrmLiteSimpleContentProvider<DatabaseHelper> {
    @Override
    protected Class<DatabaseHelper> getHelperClass() {
        return DatabaseHelper.class;
    }

    @Override
    public boolean onCreate() {
        setMatcherController(new MatcherController()//
                .add(MenuItem.class, SubType.DIRECTORY, "", MenuItemContract.CONTENT_URI_PATTERN_MANY)//
                .add(MenuItem.class, SubType.ITEM, "#", MenuItemContract.CONTENT_URI_PATTERN_ONE));
        return true;
    }
}
