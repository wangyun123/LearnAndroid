package com.example.swty_wy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int RESULT_CODE = 1234;
    private Button mButtonMusic;
    private Button mButtonHandler;
    private Button mButtonWebView;
    private Button mButtonBroadcast;
    private Button mButtonListView;
    private Button mButtonWidget;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mButtonDBbase;
    private Button mButtonNetwork;
    private Button mButtonWeather;
    private Button mButtonVolley;
    private Button mButtonContentProvider;

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

        // 数据库
        mButtonDBbase = (Button) findViewById(R.id.button_dbbase);
        mButtonDBbase.setOnClickListener(this);

        // network
        mButtonNetwork = (Button) findViewById(R.id.button_network_test);
        mButtonNetwork.setOnClickListener(this);

        // weather
        mButtonWeather = (Button) findViewById(R.id.button_weather);
        mButtonWeather.setOnClickListener(this);

        // Volley
        mButtonVolley = (Button) findViewById(R.id.button_show_activity_volley);
        mButtonVolley.setOnClickListener(this);

        mButtonContentProvider = (Button) findViewById(R.id.button_show_activity_content_provider);
        mButtonContentProvider.setOnClickListener(this);

        //Log.i(TAG, "当前路径："+getFilesDir().getAbsolutePath());
        //testFileDemo();
        testSAXParse();
        testNetwork();
    }

    private void testNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifi = networkInfo.isConnected();
    }

    private void testSAXParse() {
        SAXParserFactory saxParseFactory = SAXParserFactory.newInstance();
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
            case R.id.button_dbbase:
                startActivity(new Intent(MainActivity.this, DBbaseActivity.class));
                break;
            case R.id.button_network_test:
                startActivity(new Intent(MainActivity.this, NetworkActivity.class));
                break;
            case R.id.button_weather:
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                break;
            case R.id.button_show_activity_volley:
                startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                break;
            case R.id.button_show_activity_content_provider:
                startActivity(new Intent(MainActivity.this, ContentProviderDemoAcitvity.class));
                break;
        }
    }

    private void testFileDemo(){
        File file = new File(getFilesDir(), "test.txt");
        Log.i(TAG, "File::getFilesDir():"+getFilesDir());
        Log.i(TAG, "file.getAbsolutePath(): "+file.getAbsolutePath());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String txt = "wo cao ni daye ";
            FileOutputStream fileOutputStream = openFileOutput("test02.txt", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(txt.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
