package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SWTY-WY on 2016/8/17.
 */
public class ContentProviderDemoAcitvity extends Activity implements View.OnClickListener{

    private Button mButtonTest;
    private Button mButtonSearch;
    private Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        mButtonTest = (Button) findViewById(R.id.button_content_provider_test);
        mButtonTest.setOnClickListener(this);
        mButtonSearch = (Button) findViewById(R.id.button_content_provider_search);
        mButtonSearch.setOnClickListener(this);
        mButtonAdd = (Button) findViewById(R.id.button_content_provider_add);
        mButtonAdd.setOnClickListener(this);

        //insertRecords("MyUser");
        displayRecords();
    }

    private void displayRecords() {
        String columns[] = new String[] {MyUsers.User._ID, MyUsers.User.USER_NAME};
        Uri myUri = MyUsers.User.CONTENT_URI;
        Cursor cur = getContentResolver().query(myUri, columns, null, null, null);
        if (cur.moveToFirst()){
            String id = null;
            String userName = null;
            do {
                id = cur.getString(cur.getColumnIndex(MyUsers.User._ID));
                userName = cur.getString(cur.getColumnIndex(MyUsers.User.USER_NAME));
                Toast.makeText(this, id+" "+userName, Toast.LENGTH_LONG).show();
            }while (cur.moveToNext());
        }
    }

    private void updateRecord(int recNo, String name){

    }

    private void insertRecords(String userName) {
        ContentValues values = new ContentValues();
        values.put(MyUsers.User.USER_NAME, userName);
        getContentResolver().insert(MyUsers.User.CONTENT_URI, values);
    }

    private void deleteRecords() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_content_provider_test:
                break;
            case R.id.button_content_provider_search:
                search();
                break;
            case R.id.button_content_provider_add:
                addPeople();
                break;
        }
    }

    private void addPeople() {
        // 获取程序界面中的3个文本框
        String name = ((EditText)findViewById(R.id.contacts_name))
                .getText().toString();
        String phone = ((EditText)findViewById(R.id.contacts_phone))
                .getText().toString();
        String email = ((EditText)findViewById(R.id.contacts_email))
                .getText().toString();
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI执行一个空值插入，
        // 目的是获取系统返回的rawContactId
        Uri rawContactUri = getContentResolver()
                .insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 设置联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(
                android.provider.ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 设置联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        // 设置电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(
                android.provider.ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        // 设置联系人的Email地址
        values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
        // 设置该电子邮件的类型
        values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        // 向联系人Email URI添加Email数据
        getContentResolver().insert(
                android.provider.ContactsContract.Data.CONTENT_URI, values);
        Toast.makeText(ContentProviderDemoAcitvity.this, "联系人数据添加成功" , Toast.LENGTH_LONG).show();
     }

    private void search() {
        // 联系人
        final ArrayList<String> names = new ArrayList<String>();
        final ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);
        while(cursor.moveToFirst()){
            // 联系人id
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // 获取联系人的名字
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            names.add(name);

            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId, null,null);
            ArrayList<String> detail = new ArrayList<String>();
            while (phones.moveToFirst()){
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                detail.add("电话号码："+phoneNumber);
            }
            phones.close();

            // 使用ContentResolver查找联系人的Email地址
            Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Email.CONTACT_ID+ " = " + contactId, null, null);
            // 遍历查询结果，获取该联系人的多个Email地址
            while (emails.moveToNext())
            {
                // 获取查询结果中Email地址列中数据。
                String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                detail.add("邮件地址：" + emailAddress);
            }
            emails.close();

            details.add(detail);
        }
        cursor.close();


        //加载result.xml界面布局代表的视图
        View resultDialog = getLayoutInflater().inflate(
                R.layout.result, null);
        // 获取resultDialog中ID为list的ExpandableListView
        ExpandableListView list = (ExpandableListView)resultDialog
                .findViewById(R.id.list);
        //创建一个ExpandableListAdapter对象
        ExpandableListAdapter adapter = new BaseExpandableListAdapter()
        {
            //获取指定组位置、指定子列表项处的子列表项数据
            @Override
            public Object getChild(int groupPosition, int childPosition)
            {
                return details.get(groupPosition).get(childPosition);
            }
            @Override
            public long getChildId(int groupPosition, int childPosition)
            {
                return childPosition;
            }
            @Override
            public int getChildrenCount(int groupPosition)
            {
                return details.get(groupPosition).size();
            }
            private TextView getTextView()
            {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, 64);
                TextView textView = new TextView(ContentProviderDemoAcitvity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }
            // 该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent)
            {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }
            //获取指定组位置处的组数据
            @Override
            public Object getGroup(int groupPosition)
            {
                return names.get(groupPosition);
            }
            @Override
            public int getGroupCount()
            {
                return names.size();
            }
            @Override
            public long getGroupId(int groupPosition)
            {
                return groupPosition;
            }
            //该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent)
            {
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                return textView;
            }
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition)
            {
                return true;
            }
            @Override
            public boolean hasStableIds()
            {
                return true;
            }
        };
        // 为ExpandableListView设置Adapter对象
        list.setAdapter(adapter);
        // 使用对话框来显示查询结果。
        new AlertDialog.Builder(ContentProviderDemoAcitvity.this)
                .setView(resultDialog)
                .setPositiveButton("确定" , null)
                .show();
    }
}
