package com.nikola.WeatherApi.weather;

import android.util.Log;

import org.json.JSONObject;

public class WeatherModel {
    private String temp;

    public WeatherModel() {
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public static WeatherModel parseJSONObject(JSONObject object) {
        WeatherModel weather = new WeatherModel();
        try {
            if (object.has("main")) {
                weather.setTemp(object.getJSONObject("main").getString("temp"));
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        return weather;
    }
}