package com.example.swty_wy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int RESULT_CODE = 1234;
    private Button mButtonMusic;
    private Button mButtonHandler;
    private Button mButtonWebView;
    private Button mButtonBroadcast;
    private Button mButtonListView;
    private Button mButtonWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button03).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(SplashActivity.TITLE, "i be back");
                setResult(RESULT_CODE, intent);
                finish();
            }
        });

        // Handler
        mButtonHandler = (Button) findViewById(R.id.button_handler);
        mButtonHandler.setOnClickListener(this);

        // 音乐播放器
        mButtonMusic = (Button) findViewById(R.id.button_music);
        mButtonMusic.setOnClickListener(this);

        // WebView
        mButtonWebView = (Button) findViewById(R.id.button_web_view);
        mButtonWebView.setOnClickListener(this);

        // Broadcast
        mButtonBroadcast = (Button) findViewById(R.id.button_broadcast);
        mButtonBroadcast.setOnClickListener(this);

        // ListView
        mButtonListView = (Button) findViewById(R.id.button_list_view);
        mButtonListView.setOnClickListener(this);

        // Widget
        mButtonWidget = (Button) findViewById(R.id.button_widget);
        mButtonWidget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_handler:
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.button_music:
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                break;
            case R.id.button_web_view:
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                break;
            case R.id.button_broadcast:
                startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
                break;
            case R.id.button_list_view:
                startActivity(new Intent(MainActivity.this, ListViewDemoActivity.class));
                break;
            case R.id.button_widget:
                startActivity(new Intent(MainActivity.this, TestWidget.class));
                break;
        }
    }
}
