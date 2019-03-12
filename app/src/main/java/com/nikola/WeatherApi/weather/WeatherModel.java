package com.nikola.WeatherApi.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class WeatherModel {
    private String temp;

    public WeatherModel() {
    }

    public WeatherModel(String temp) {
        this.temp = temp;
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

    public static LinkedList<WeatherModel> parseJSONArray(JSONArray array){
        LinkedList<WeatherModel> list = new LinkedList<>();

        try{
            for(int i=0; i < array.length();i++){
                WeatherModel weather = parseJSONObject(array.getJSONObject(i));
                list.add(weather);
            }
        } catch (Exception e){
            Log.e("Exception", e.getMessage());
        }
        return list;
    }
}