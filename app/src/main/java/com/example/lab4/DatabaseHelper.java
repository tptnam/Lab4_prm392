package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "food";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFood(obj food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_DESCRIPTION, food.getDescription());
        values.put(COLUMN_IMAGE, food.getImage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<obj> getAllFood() {
        ArrayList<obj> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_IMAGE}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                obj food = new obj(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }


}
