package com.nikola.WeatherApi.weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApi {
    public static void getJSON(String url, final ReadDataHandler rdh) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            String result = "";

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String row;
                    while ((row = br.readLine()) != null) {
                        result += row + "\n";
                    }
                    br.close();
                } catch (Exception e) {
                    result = "[]";
                    Log.e("Exception", e.getMessage());
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                rdh.setJson(result);
                rdh.sendEmptyMessage(0);
            }
        };
        task.execute(url);
    }
}