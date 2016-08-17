package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SWTY-WY on 2016/8/15.
 */
public class NetworkActivity extends Activity implements View.OnClickListener{

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        
        findViews();
        setListeners();
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private void findViews() {
        mEditText = (EditText) findViewById(R.id.edit_text_input_network);
        mTextView = (TextView) findViewById(R.id.test_view_network);
        mButton = (Button) findViewById(R.id.button_network);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_network:
                String url = getEditTextUrl();
                new RequestNetworkDataTask().execute(url);
                break;
        }
    }

    private String getEditTextUrl() {
        return mEditText != null ? mEditText.getText().toString() : "";
    }

    private String requestData(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("GET");  // GET POST

            connection.connect();
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            String result = null;
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                char[] buffer = new char[1024];
                reader.read(buffer);
                result = new String(buffer);
            } else {

            }


            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    class RequestNetworkDataTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 在后台之前，主线程
            // UI loading
        }

        @Override
        protected String doInBackground(String[] params) {
            String result = requestData(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 在执行完 主线程
            mTextView.setText(s);
        }
    }
}
