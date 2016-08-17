package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by SWTY-WY on 2016/8/17.
 */
public class VolleyActivity extends Activity implements View.OnClickListener{
    private static final String TAG="VolleyActivity";
    private Button mButtonRequest;
    private Button mButtonJsonRequest;
    private RequestQueue mRequestQueue;
    private Button mButtonImageRequest;
    private ImageView mImageViewRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        mButtonRequest = (Button) findViewById(R.id.button_volley_request);
        mButtonJsonRequest = (Button) findViewById(R.id.button_volley_jsonrequest);
        mButtonImageRequest = (Button) findViewById(R.id.button_volley_imagerequest);
        mButtonRequest.setOnClickListener(this);
        mButtonJsonRequest.setOnClickListener(this);
        mButtonImageRequest.setOnClickListener(this);

        mImageViewRequest = (ImageView) findViewById(R.id.image_view_volley_image_request);

        mRequestQueue = Volley.newRequestQueue(VolleyActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_volley_request:

                StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "onResponse: "+response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: "+error.getMessage());
                    }
                });

                mRequestQueue.add(stringRequest);
                break;
            case R.id.button_volley_jsonrequest:
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/mweather/101010100.shtml", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "onResponse: " + response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: "+error.getMessage(), error);
                    }
                });
                mRequestQueue.add(jsonObjectRequest);
                break;
            case R.id.button_volley_imagerequest:
                ImageRequest imageRequest = new ImageRequest("http://m.baidu.com/static/index/u.png",
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                mImageViewRequest.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mImageViewRequest.setImageResource(R.drawable.ditu);
                    }
                });
                mRequestQueue.add(imageRequest);
                break;
        }
    }
}
