package com.nikola.mobilniFarmer;

import android.animation.LayoutTransition;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikola.culture.Culture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CultureViewActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Integer> images;
    ArrayList<String> names;

    static HashSet<Culture> cultures = new HashSet<>(0);
    static HashMap<String, Integer> cultureInfo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_view);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            cultureInfo = (HashMap<String, Integer>) bundle.get("cultureInfo");
            if (cultureInfo != null) {
                names = new ArrayList<>(cultureInfo.keySet());
                images = new ArrayList<>(cultureInfo.values());
            }
        }


        listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.size();
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
            View v = getLayoutInflater().inflate(R.layout.culture_view_element, null);

            ImageView elemImg = (ImageView) v.findViewById(R.id.listElementImg);
            TextView elemTxt = (TextView) v.findViewById(R.id.listElementName);
            TextView adviceTxt = (TextView) v.findViewById(R.id.adviceTxt);

            elemImg.setImageResource(images.get(position));
            elemTxt.setText(names.get(position));
            adviceTxt.setText(R.string.lorem);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout txtLayout = (LinearLayout) v.findViewById(R.id.adviceLayout);
                    ViewGroup.LayoutParams params = txtLayout.getLayoutParams();
                    txtLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

                    if (params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    } else params.height = 0;

                    txtLayout.setLayoutParams(params);
                }
            });

            return v;
        }
    }
}
