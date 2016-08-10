package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SWTY-WY on 2016/7/20.
 */
public class ListViewDemoActivity extends Activity implements View.OnClickListener{

    public static final String TAG = ListViewDemoActivity.class.getSimpleName();
    private ListView mListView=null;
    private int mDataCounts=5;
    private EditText mEditTextCounts;
    private Button mButtonConfirm;
    private List<UserInfo> mListUsers;
    private ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);

        findViews();
        setData();
        setListListeners();
    }

    private void setListListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "item_list:"+i);
                Toast.makeText(ListViewDemoActivity.this, mListUsers.get(i).getName()+"击中", Toast.LENGTH_LONG).show();
            }
        });

        mButtonConfirm.setOnClickListener(this);
    }

    private void setData() {
        getData2Preference();
        mEditTextCounts.setText(String.valueOf(mDataCounts));
        mListUsers = new ArrayList<>();
        for (int i = 0; i < mDataCounts; i++) {
            mListUsers.add(new UserInfo("fuck",21));
        }
        mContactAdapter = new ContactAdapter(ListViewDemoActivity.this, mListUsers);
        mListView.setAdapter(mContactAdapter);
    }

    private void findViews() {
        mListView = (ListView) findViewById(R.id.list_view_demo);
        mEditTextCounts = (EditText) findViewById(R.id.data_counts_edit_text);
        mButtonConfirm = (Button) findViewById(R.id.confirm_button);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_button:
                String countString = mEditTextCounts.getText().toString();
                mDataCounts = Integer.valueOf(countString);
                refreshListView();
                saveData2Preference(mDataCounts);
                break;
        }
    }

    private void saveData2Preference(int num) {
        //系统帮助创建一个xml文件"preference_name"
        SharedPreferences sharePreferences = getSharedPreferences("preference_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putInt("count", mDataCounts);
        //editor.commit();
        editor.apply();
    }

    private void getData2Preference(){
        SharedPreferences sharePreferences = getSharedPreferences("preference_name", Context.MODE_PRIVATE);
        mDataCounts = sharePreferences.getInt("count",1);
    }
    private void refreshListView() {
        mListUsers.clear();
        for (int i = 0; i < mDataCounts; i++) {
            mListUsers.add(new UserInfo("ritage",22));
        }
        mContactAdapter.refreshData(mListUsers);
        mContactAdapter.notifyDataSetChanged();
    }
}
