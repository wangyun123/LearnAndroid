package com.example.swty_wy.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by SWTY-WY on 2016/8/4.
 */
public class FirstReceiver extends BroadcastReceiver {
    private static final String TAG = "NormalBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i(TAG, "FirstReceiver: " + msg);
    }
}
