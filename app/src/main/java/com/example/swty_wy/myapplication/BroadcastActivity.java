package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by SWTY-WY on 2016/8/4.
 */

public class BroadcastActivity extends Activity implements View.OnClickListener{

    private MyReceiver mReceiver;
    private Button mButtonSend;
    private Button mButtonRegister;
    private FirstReceiver mFirstReceiver;
    private SecondReceiver mSecondReceiver;
    private ThirdReceiver mThirdReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mButtonRegister = (Button) findViewById(R.id.button_register_broadcast);
        mButtonSend = (Button) findViewById(R.id.button_send_broadcast);

        mButtonRegister.setOnClickListener(this);
        mButtonSend.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 动态取消注册Broadcast Receiver
        unregisterReceiver(mReceiver);
        unregisterReceiver(mFirstReceiver);
        unregisterReceiver(mSecondReceiver);
        unregisterReceiver(mThirdReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_register_broadcast:
                // 动态注册Broadcast Receiver
                Toast.makeText(BroadcastActivity.this, "BroadcastActivity Register", Toast.LENGTH_LONG).show();
                mReceiver = new MyReceiver();
                mFirstReceiver = new FirstReceiver();
                mSecondReceiver = new SecondReceiver();
                mThirdReceiver = new ThirdReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.intent.action.MY_BROADCAST");
                registerReceiver(mReceiver, filter);
                registerReceiver(mFirstReceiver, filter);
                registerReceiver(mSecondReceiver, filter);
                registerReceiver(mThirdReceiver, filter);
                break;
            case R.id.button_send_broadcast:
                Toast.makeText(BroadcastActivity.this, "BroadcastActivity Send", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("android.intent.action.MY_BROADCAST");
                intent.putExtra("msg", "hello receiver");
                sendBroadcast(intent);
                break;
        }
    }
}
