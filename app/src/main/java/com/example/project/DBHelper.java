package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HealthCare.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table USERS (id integer primary key, name text, email text unique, password text)");
        db.execSQL("create table HWA (email text primary key, height number default 0, weight number default 0, age number default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS HWA");
        onCreate(db);
    }

    public boolean insertUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("USERS", null, contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("email", email);
        db.insert("HWA", null, contentValues2);
        return true;
    }

    public boolean addHWA(String email, int height, int weight, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        contentValues.put("age", age);
        db.update("HWA", contentValues, "email=?", new String[]{email});
        return true;
    }

    public int getHeight(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select height from HWA where lower(email)=lower('" + email + "')", null );
        if (res.getCount() == 0)
            return 0;
        res.moveToFirst();
        return res.getInt(res.getColumnIndex("height"));
    }

    public int getWeight(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select weight from HWA where lower(email)=lower('" + email + "')", null );
        if (res.getCount() == 0)
            return 0;
        res.moveToFirst();
        return res.getInt(res.getColumnIndex("weight"));
    }

    public int getAge(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select age from HWA where lower(email)=lower('" + email + "')", null );
        if (res.getCount() == 0)
            return 0;
        res.moveToFirst();
        return res.getInt(res.getColumnIndex("age"));
    }

    public String getPassword(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from USERS where lower(email)=lower('" + email + "')", null );

        if (res.getCount() == 0)
            return "";

        res.moveToFirst();
        return res.getString(res.getColumnIndex("password"));
    }
}