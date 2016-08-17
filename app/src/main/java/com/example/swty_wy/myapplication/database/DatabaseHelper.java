package com.example.swty_wy.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SWTY-WY on 2016/8/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table user(username varchar(20) not null, password varchar(60) not null);");
        sqLiteDatabase.execSQL("create table " +  TABLE_NAME + "(" + USERNAME + " varchar(20) not null, " + PASSWORD + " varchar(60) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
