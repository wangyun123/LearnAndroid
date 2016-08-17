package com.example.swty_wy.myapplication;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by SWTY-WY on 2016/8/17.
 */
public class MyUsers {
    public static final String AUTHORITY  = "com.example.swty_wy.myapplication.MyContentProvider";

    // BaseColumn类中已经包含了 _id字段
    public static final class User implements BaseColumns {
        public static final Uri CONTENT_URI  = Uri.parse("content://com.example.swty_wy.myapplication.MyContentProvider");
        // 表数据列
        public static final String  USER_NAME  = "USER_NAME";
    }
}
