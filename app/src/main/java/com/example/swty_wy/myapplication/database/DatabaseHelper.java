package com.example.swty_wy.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SWTY-WY on 2016/8/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table user(username varchar(20) not null, password varchar(60) not null);");
        //sqLiteDatabase.execSQL("create table " +  TABLE_NAME + "(" + USERNAME + " varchar(20) not null, " + PASSWORD + " varchar(60) not null);");
        sqLiteDatabase.execSQL("create table" + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
