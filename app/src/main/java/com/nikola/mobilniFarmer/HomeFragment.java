package com.nikola.mobilniFarmer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    static String landAreaWheat;
    static String landAreaBarley;
    static String landAreaCorn;
    static String landAreaSoy;
    static String landAreaSunflower;

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
        btnWeather.setOnClickListener(this);
        ImageButton btnCulture = (ImageButton) v.findViewById(R.id.imgBtnCulture);
        btnCulture.setOnClickListener(this);*/


        v.findViewById(R.id.imgBtnBanner1).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner2).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner3).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner4).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner5).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner6).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner7).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner8).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner9).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner10).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner11).setOnClickListener(banners);
        v.findViewById(R.id.imgBtnBanner12).setOnClickListener(banners);


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
