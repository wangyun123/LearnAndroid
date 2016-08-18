package com.example.swty_wy.myapplication.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.swty_wy.myapplication.R;

/**
 * Created by SWTY-WY on 2016/8/18.
 */
public class ResolverDemoActivity extends Activity implements View.OnClickListener{
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private EditText mEditTextName;
    private EditText mEditTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolver);

        listView=(ListView) this.findViewById(R.id.list_view_persons);
        ContentResolver contentResolver = getContentResolver();
        Uri selectUri = Uri.parse("content://com.example.swty_wy.myapplication.contentprovider/person");
        Cursor cursor=contentResolver.query(selectUri, null, null, null, null);

        adapter = new SimpleCursorAdapter(this, R.layout.item_person, cursor,
                new String[]{"_id", "name", "age"},
                new int[]{R.id.person_id, R.id.person_name, R.id.person_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lView = (ListView)parent;
                Cursor data = (Cursor)lView.getItemAtPosition(position);
                int _id = data.getInt(data.getColumnIndex("_id"));
                Toast.makeText(ResolverDemoActivity.this, _id+"", Toast.LENGTH_SHORT).show();
            }
        });

        Button mButtonAddPerson = (Button) findViewById(R.id.button_add_person);
        mButtonAddPerson.setOnClickListener(this);

        Button mButtonSearchPerson = (Button) findViewById(R.id.button_search_person);
        mButtonSearchPerson.setOnClickListener(this);

        mEditTextName = (EditText) findViewById(R.id.edit_text_person_name);
        mEditTextAge = (EditText) findViewById(R.id.edit_text_person_age);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_person:
                ContentResolver contentResolver = getContentResolver();
                Uri insertUri = Uri.parse("content://com.example.swty_wy.myapplication.contentprovider/person");
                ContentValues values = new ContentValues();
                values.put("name", mEditTextName.getText().toString());
                values.put("age", mEditTextAge.getText().toString());
                Uri uri = contentResolver.insert(insertUri, values);
                Toast.makeText(ResolverDemoActivity.this, "添加完成", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_search_person:
                break;
        }
    }
}
