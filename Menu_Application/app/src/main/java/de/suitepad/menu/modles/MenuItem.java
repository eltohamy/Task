package de.suitepad.menu.modles;

import android.database.Cursor;

/**
 * Created by Tohamy.Allam on 4/19/2016.
 */
public class MenuItem {
    private int id;

    private String name;

    private double price;

    private int type;

    //return MenuItem from cursor.
    public static MenuItem fromCursor(Cursor cursor) {
        final MenuItem menuItem = new MenuItem();
        menuItem.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        menuItem.setName(cursor.getString(cursor.getColumnIndex("name")));
        menuItem.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
        menuItem.setType(cursor.getInt(cursor.getColumnIndex("type")));
        return menuItem;
    }

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
