package com.example.swty_wy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SWTY-WY on 2016/7/26.
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext=null;
    private LayoutInflater mLayoutInflater = null;

    private String mNames[] = {"fuck", "niubi"};
    private List<UserInfo> mListUsers = new ArrayList<>();

    public ContactAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < 10; i++) {
            mListUsers.add(new UserInfo("fuck",21));
        }
    }

    @Override
    public int getCount() {
        return mListUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return mListUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 返回一个视图
        view = mLayoutInflater.inflate(R.layout.item_contact,null);
        // 获取控件
        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_list);
        TextView ageTextView = (TextView) view.findViewById(R.id.age_text_list);
        ImageView avatarImageView = (ImageView) view.findViewById(R.id.avatar_image_view);
        // 数据与控件的绑定
        nameTextView.setText(mListUsers.get(i).getName());
        ageTextView.setText(String.valueOf(mListUsers.get(i).getAge()));
        avatarImageView.setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}
