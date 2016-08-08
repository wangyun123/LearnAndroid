package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by SWTY-WY on 2016/7/20.
 */
public class ListViewDemoActivity extends Activity {

    public static final String TAG = ListViewDemoActivity.class.getSimpleName();
    private ListView mListView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);

        mListView = (ListView) findViewById(R.id.list_view_demo);
        ContactAdapter contactAdapter = new ContactAdapter(ListViewDemoActivity.this);
        mListView.setAdapter(contactAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "item_list:"+i);
                Toast.makeText(ListViewDemoActivity.this, "fuck:", Toast.LENGTH_LONG).show();
            }
        });
    }
}
