package com.nikola.WeatherApi.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastModel {
    private String temp, city, desc, icon;
    private static ArrayList<ForecastModel> forecastList;

    public ForecastModel() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public String getDesc() {
        return desc;
    }

    public String getIcon() {
        return icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public static ArrayList<ForecastModel> parseJSONObject(JSONObject object) {
        forecastList = new ArrayList<>();
        try {
            JSONArray array = object.getJSONArray("list");
            for(int i=0; i < array.length();){
                Log.e("INTEGER", String.valueOf(i));
                ForecastModel forecast = new ForecastModel();
                forecast.setCity(object.getJSONObject("city").getString("name"));
                JSONObject json = array.getJSONObject(i);
                forecast.setTemp(json.getJSONObject("main").getString("temp"));
                forecast.setDesc(json.getJSONArray("weather").getJSONObject(0).getString("description"));
                forecast.setIcon(json.getJSONArray("weather").getJSONObject(0).getString("icon"));
                forecastList.add(forecast);
                i = i + 8;
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return forecastList;
    }
}
