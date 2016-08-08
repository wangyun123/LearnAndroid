package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by SWTY-WY on 2016/7/25.
 */
public class SplashActivity extends Activity {
    public static final int REQUEST_CODE = 9999;
    private  static final String TAG = SplashActivity.class.getSimpleName();
    public static final String TITLE = "title";
    private Button mButton01;
    private Button mButton02;

    Handler mHandler = new Handler();

    private View.OnClickListener mListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mButton01 = (Button)findViewById(R.id.button01);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo = new UserInfo("傻逼",11);
                //
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(TITLE, mButton01.getText().toString());
                intent.putExtra("userInfo", userInfo);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);
            }
        }, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestCode:"+requestCode+",resultCode:" +resultCode);

        if (requestCode == REQUEST_CODE && resultCode==MainActivity.RESULT_CODE){
            if (data != null){
                String title = data.getStringExtra(TITLE);
                Toast.makeText(SplashActivity.this, title, Toast.LENGTH_LONG).show();
            }
        }
    }
}
