package com.nikola.mobilniFarmer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nikola.culture.Culture;

import java.util.HashMap;
import java.util.HashSet;

public class HomeFragment extends Fragment implements View.OnClickListener {

    static String landAreaWheat;
    static String landAreaBarley;
    static String landAreaCorn;
    static String landAreaSoy;
    static String landAreaSunflower;
    static HashSet<Culture> cultures = new HashSet<>(0);
    static HashMap<String, Integer> cultureInfo = new HashMap<>(0);

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        Bundle bundle = getActivity().getIntent().getExtras();

        if(bundle!=null){

            switch (bundle.getInt("id")){
                case R.id.imgBtnWheat:{
                    landAreaWheat = bundle.getString("landArea");
                    break;
                }
                case R.id.imgBtnBarley:{
                    landAreaBarley = bundle.getString("landArea");
                    break;
                }
                case R.id.imgBtnCorn:{
                    landAreaCorn = bundle.getString("landArea");
                    break;
                }
                case R.id.imgBtnSoy:{
                    landAreaSoy = bundle.getString("landArea");
                    break;
                }
                case R.id.imgBtnSunflower:{
                    landAreaSunflower = bundle.getString("landArea");
                    break;
                }
            }
        cultureInfo = (HashMap<String,Integer>)bundle.get("cultureInfo");
        cultures=(HashSet<Culture>) bundle.get("cultureSet");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        ((TextView)v.findViewById(R.id.txtWheat)).setText(landAreaWheat);
        ((TextView)v.findViewById(R.id.txtBarley)).setText(landAreaBarley);
        ((TextView)v.findViewById(R.id.txtCorn)).setText(landAreaCorn);
        ((TextView)v.findViewById(R.id.txtSoy)).setText(landAreaSoy);
        ((TextView)v.findViewById(R.id.txtSunflower)).setText(landAreaSunflower);


        ((ImageButton)v.findViewById(R.id.imgBtnWheat)).setOnClickListener(this);
        ((ImageButton)v.findViewById(R.id.imgBtnBarley)).setOnClickListener(this);
        ((ImageButton)v.findViewById(R.id.imgBtnCorn)).setOnClickListener(this);
        ((ImageButton)v.findViewById(R.id.imgBtnSoy)).setOnClickListener(this);
        ((ImageButton)v.findViewById(R.id.imgBtnSunflower)).setOnClickListener(this);


        /*ImageButton btnWeather = (ImageButton) v.findViewById(R.id.imgBtnWeather);
        btnWeather.setOnClickListener(this);*/

        ((ImageButton) v.findViewById(R.id.imgBtnCulture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CultureViewActivity.class);
                i.putExtra("cultureSet",cultures);
                i.putExtra("cultureInfo", cultureInfo);
                startActivity(i);
            }
        });

        // Adding listeners for each Advertisement banner

        ImageButton[] buttons = new ImageButton[13];
        for(int i=1; i<buttons.length; i++) {
            String buttonID = "imgBtnBanner" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
            buttons[i] = ((ImageButton) v.findViewById(resID));
            buttons[i].setOnClickListener(banners);
        }

        return v;
    }


    View.OnClickListener banners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ((String)v.getTag()){
                case "delta agrar":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.delta_agrar_link)));
                    startActivity(browserIntent);
                    break;
                }
                case "mk group":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.mk_group_link)));
                    startActivity(browserIntent);
                    break;
                }
                case "syngenta":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.syngenta_link)));
                    startActivity(browserIntent);
                    break;
                }
                case "bayer":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.bayer_link)));
                    startActivity(browserIntent);
                    break;
                }
                case "kite":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.kite_link)));
                    startActivity(browserIntent);
                    break;
                }
                case "pioneer":{
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.pioneer_link)));
                    startActivity(browserIntent);
                    break;
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.buttonPressed((ImageButton)v);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void buttonPressed(ImageButton imgBtn);
    }
}
