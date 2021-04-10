package com.example.project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HealthCare.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table USERS (id integer primary key, name text, email text unique, password text)");
        db.execSQL("create table HWA (email text primary key, height number default 0, weight number default 0, age number default 0)");
        db.execSQL("create table BSC (email text, date datetime default current_timestamp, BP number default 0, sugar number default 0, calorie number default 0, PRIMARY KEY (email, date))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS HWA");
        db.execSQL("DROP TABLE IF EXISTS BSC");
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

    public boolean addBSC(String email, int BP, int sugar, int cal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("email", email);
        Values.put("BP", BP);
        Values.put("Sugar", sugar);
        Values.put("Calorie", cal);
        db.insert("BSC", null, Values);
        return true;
    }

    public boolean changePwd(String email, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", pwd);
        db.update("USERS", contentValues, "email=?", new String[]{email});
        return true;
    }

    public String getdata(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from BSC WHERE lower(email)=lower('" + email + "')", null );
        if (res.getCount() == 0)
            return "";
        res.moveToFirst();
        String row_values = "";
        do {
            for(int i = 0 ; i < res.getColumnCount(); i++)
                row_values += res.getString(i) + " | ";
            row_values += '\n';
        } while (res.moveToNext());
        return row_values;
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