package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.swty_wy.myapplication.database.DatabaseHelper;

/**
 * Created by SWTY-WY on 2016/8/12.
 */
public class DBbaseActivity extends Activity{

    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_db_base);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getWritableDatabase();

        findViewById(R.id.button_dbbase_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.USERNAME, "我靠");
                contentValues.put(DatabaseHelper.PASSWORD, "123456");
                long rowNumber = mSqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
                if (rowNumber != -1){
                    Toast.makeText(DBbaseActivity.this, "插成功", Toast.LENGTH_LONG).show();
                }
            }
        });

        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null,null);
    }
}
