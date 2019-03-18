package com.nikola.WeatherApi.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastModel {
    private String temp, city, desc, icon, date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
            for (int i = 0; i < array.length(); i += 8) {
                Log.e("INTEGER", String.valueOf(i));
                ForecastModel forecast = new ForecastModel();
                forecast.setCity(object.getJSONObject("city").getString("name"));
                JSONObject json = array.getJSONObject(i);
                forecast.setDate(json.getString("dt_txt"));
                forecast.setTemp(json.getJSONObject("main").getString("temp"));
                forecast.setDesc(json.getJSONArray("weather").getJSONObject(0).getString("description"));
                forecast.setIcon(json.getJSONArray("weather").getJSONObject(0).getString("icon"));
                forecastList.add(forecast);
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return forecastList;
    }
}
