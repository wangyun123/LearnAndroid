package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SWTY-WY on 2016/8/2.
 */
public class MusicActivity extends Activity implements View.OnClickListener{

    private Button mButtonStart;
    private Button mButtonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mButtonStart = (Button) findViewById(R.id.button_music_start);
        mButtonStop = (Button) findViewById(R.id.button_music_stop);
        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_music_start:
                startService(new Intent(MusicActivity.this, MusicService.class));
                break;
            case R.id.button_music_stop:
                stopService(new Intent(MusicActivity.this, MusicService.class));
                break;
        }
    }
}
