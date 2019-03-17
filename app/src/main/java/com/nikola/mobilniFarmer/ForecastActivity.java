package com.nikola.mobilniFarmer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nikola.WeatherApi.weather.ForecastModel;
import com.nikola.WeatherApi.weather.ReadDataHandler;
import com.nikola.WeatherApi.weather.WeatherApi;
import com.nikola.WeatherApi.weather.WeatherModel;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity {

    private String lat, lng;
    private ArrayList<ForecastModel> forecastList;


    TextView cityName;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_view);

        cityName = (TextView) findViewById(R.id.cityName);
        listView = (ListView) findViewById(R.id.listView);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            lat = String.valueOf(b.getDouble("latitude"));
            lng = String.valueOf(b.getDouble("longitude"));
        }

        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat
                + "&lon=" + lng + "&units=metric&appid=0c90c6c98f912f14896425d39d3ba458";

        WeatherApi.getJSON(url, new ReadDataHandler() {

                    @Override
                    public void handleMessage(Message msg) {
                        try {
                            forecastList = new ArrayList<>();
                            JSONObject json = new JSONObject(getJson());
                            forecastList = ForecastModel.parseJSONObject(json);
                            cityName.setText(forecastList.get(0).getCity());
                            ForecastAdapter forecastAdapter = new ForecastAdapter();
                            listView.setAdapter(forecastAdapter);
                        } catch (Exception e) {
                            Log.e("Exception", e.getMessage());
                        }
                    }


                }
        );
    }


    class ForecastAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return forecastList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.forecast_view_element,null);

            ImageView icon = (ImageView)v.findViewById(R.id.forecastElementImg);
            TextView temp = (TextView)v.findViewById(R.id.listElementTemp);

            String imgUrlRoot = "http://openweathermap.org/img/w/";
            String imgUrlSufix = forecastList.get(position).getIcon() + ".png";

            try {
                URL url = new URL(imgUrlRoot + imgUrlSufix);
 //               Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
 //               icon.setImageBitmap(bmp);
                temp.setText(String.format("%.0f", Double.valueOf(forecastList.get(position).getTemp())) + " \u2103");
            }
            catch (Exception e){
                Log.e("Exception", e.getMessage());
            }
            return v;
        }
    }
}
