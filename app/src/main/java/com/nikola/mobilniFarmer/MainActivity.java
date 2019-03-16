package com.nikola.mobilniFarmer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nikola.WeatherApi.weather.ReadDataHandler;
import com.nikola.WeatherApi.weather.WeatherApi;
import com.nikola.WeatherApi.weather.WeatherModel;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements LocationListener, HomeFragment.OnFragmentInteractionListener {

    private TextView temperature;
    Location location;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    getWeather(location);
                    break;
                case R.id.nav_book:
                    selectedFragment = new BookFragment();
                    break;
                case R.id.nav_shop:
                    selectedFragment = new ShopFragment();
                    break;
                case R.id.nav_machine:
                    selectedFragment = new MachineFragment();
                    break;
                case R.id.nav_calculator:
                    selectedFragment = new CalcFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        getLocation();


    }


    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("Denied", "Permision denied");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 500, 10, this);
            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            getWeather(location);

        }

    }


    @Override
    public void onLocationChanged(Location location) {
        getWeather(location);
    }

    public void getWeather(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(lat)
                + "&lon=" + String.valueOf(lon) + "&units=metric&appid=0c90c6c98f912f14896425d39d3ba458";

        WeatherApi.getJSON(url, new ReadDataHandler() {
                    @Override
                    public void handleMessage(Message msg) {
                        try {
                            temperature = (TextView) findViewById(R.id.temperature);
                            JSONObject json = new JSONObject(getJson());
                            WeatherModel weather = WeatherModel.parseJSONObject(json);
                            String rounded = String.format("%.0f", Double.valueOf(weather.getTemp()));
                            temperature.setText(rounded + " \u2103");
                        } catch (Exception e) {
                            Log.e("Exception", e.getMessage());
                        }
                    }
                }
        );

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public void buttonPressed(ImageButton imgBtn) {
        Intent cultureEditIntent = new Intent(this, CultureEditActivity.class);

        int id = imgBtn.getId();
        String name = (String) imgBtn.getTag();

        cultureEditIntent.putExtra("cultureName", imgBtn.getContentDescription());
        cultureEditIntent.putExtra("cultureImg", getResources().getIdentifier(name, "drawable", getPackageName()));
        cultureEditIntent.putExtra("id", id);


        startActivity(cultureEditIntent);

    }

}
