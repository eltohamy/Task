package de.suitepad.support.models;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultContentMimeTypeVnd;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultContentUri;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultSortOrder;

import de.suitepad.support.provider.MenuItemContract;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
@DatabaseTable(tableName = MenuItemContract.TABLE_NAME)
@DefaultContentUri(authority = MenuItemContract.AUTHORITY, path = MenuItemContract.CONTENT_URI_PATH)
@DefaultContentMimeTypeVnd(name = MenuItemContract.MIMETYPE_NAME, type = MenuItemContract.MIMETYPE_TYPE)
public class MenuItem {
    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    @DefaultSortOrder
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private double price;

    @DatabaseField
    private int type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
