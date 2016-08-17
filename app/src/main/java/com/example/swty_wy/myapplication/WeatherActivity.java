package com.example.swty_wy.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swty_wy.myapplication.weather.JavaBean;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by SWTY-WY on 2016/8/16.
 */
public class WeatherActivity extends Activity implements View.OnClickListener {

    private Button mButtonWeatherGet;
    private TextView mTextViewWeather;
    private static final String TAG = "WeatherActivity";
    private Button mButtonWeatherParse;
    private static String mJson;
    private RequestNetworkTask mRequestNetworkTask;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView mTextViewToday;
    private TextView mTextViewNext1day;
    private TextView mTextViewNext2day;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_weather_get:
                Toast.makeText(this, "点击获取网址", Toast.LENGTH_LONG).show();
                String httpUrl = "http://apis.baidu.com/heweather/pro/attractions";
                String httpArg = "cityid=CN10101010018A";

                mRequestNetworkTask = new RequestNetworkTask();
                mRequestNetworkTask.execute(httpUrl, httpArg);
                break;
            case R.id.button_weather_parse:
                //Toast.makeText(this, "解析网址", Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                String json = mJson;
                Log.i(TAG, "response: " + json);
                JavaBean weatherInfo = new JavaBean();
                weatherInfo = gson.fromJson(json, JavaBean.class);
                //Log.i(TAG, "result: "+weatherInfo.getHeWeatherdataservice().get(0).getBasic().getCity());

                List<JavaBean.HeWeatherdataserviceBean> list = weatherInfo.getHeWeatherdataservice();
                if (list != null && list.size()>0){
                    Log.i(TAG, "result: "+list.get(0).getBasic().getCity());
                    List<JavaBean.HeWeatherdataserviceBean.DailyForecastBean> listForce = list.get(0).getDaily_forecast();
                    Log.i(TAG, "result: "+listForce.get(0).getDate());
                    if (listForce != null && list.size()>0){
                        Log.i(TAG, "result: "+listForce.get(0).getCond().getTxt_d());
                        mTextViewToday.append(listForce.get(0).getCond().getTxt_d());
                        mTextViewNext1day.append(listForce.get(1).getCond().getTxt_d());
                        mTextViewNext2day.append(listForce.get(2).getCond().getTxt_d());
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //222545b82d4999f6e8636236bc1f86b5

        mButtonWeatherGet = (Button) findViewById(R.id.button_weather_get);
        mButtonWeatherGet.setOnClickListener(this);
        mTextViewWeather = (TextView) findViewById(R.id.edit_text_result);

        mButtonWeatherParse = (Button) findViewById(R.id.button_weather_parse);
        mButtonWeatherParse.setOnClickListener(this);

        mTextViewToday = (TextView) findViewById(R.id.text_view_forecast_today);
        mTextViewNext1day = (TextView) findViewById(R.id.text_view_forecast_next1day);
        mTextViewNext2day = (TextView) findViewById(R.id.text_view_forecast_next2day);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * @param urlAll  :请求接口
     * @param httpArg :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            //connection.setRequestProperty("apikey",  "您自己的apikey");
            connection.setRequestProperty("apikey", "222545b82d4999f6e8636236bc1f86b5");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Weather Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.swty_wy.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Weather Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.swty_wy.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class RequestNetworkTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String tmp = s.replace(" ", "");
            mJson = tmp.replace("3.0","");
            Log.i(TAG, "onPostExecute: " + mJson);
            mTextViewWeather.setText(mJson);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = request(params[0], params[1]);
            return result;
        }
    }
}
