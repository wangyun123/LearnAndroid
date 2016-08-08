package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SWTY-WY on 2016/8/2.
 */
public class HandlerActivity extends Activity {
    private TextView mTextView;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1003:
                    Toast.makeText(HandlerActivity.this, "handle message", Toast.LENGTH_LONG).show();
                    int val = (int) msg.obj;
                    mTextView.setText(String.valueOf(val));

                    msg = mHandler.obtainMessage();
                    msg.arg1 = 1001;
                    msg.arg2 = 1002;
                    msg.what = 1003;
                    msg.obj = val-1;
                    if (val>0){
                        sendMessageDelayed(msg, 1000);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = (TextView) findViewById(R.id.handler_text_view_count);

        Message msg = mHandler.obtainMessage();
        msg.arg1 = 1001;
        msg.arg2 = 1002;
        msg.what = 1003;
        msg.obj = 1004;
        mHandler.sendMessageDelayed(msg, 1000);
    }
}
