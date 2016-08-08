package com.example.swty_wy.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by SWTY-WY on 2016/8/4.
 */
public class MsgPushService extends Service{

    private static final String TAG = "MsgPushService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate called.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand called.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
