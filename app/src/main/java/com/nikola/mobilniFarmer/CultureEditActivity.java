package com.nikola.mobilniFarmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikola.culture.Barley;
import com.nikola.culture.Corn;
import com.nikola.culture.Culture;
import com.nikola.culture.Soy;
import com.nikola.culture.Sunflower;
import com.nikola.culture.Wheat;

import java.util.HashMap;
import java.util.HashSet;

public class CultureEditActivity extends AppCompatActivity implements View.OnClickListener {

    Wheat wheat;
    Barley barley;
    Corn corn;
    Soy soy;
    Sunflower sunflower;
    EditText inputLandArea;
    HashSet<Culture> cultures;
    HashMap<String, Integer> cultureInfo;

    int id = 0;
    String cultureName;
    int cultureImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_edit);
        Bundle bundle = getIntent().getExtras();

        inputLandArea = (EditText) findViewById(R.id.input_land_area);

        if (bundle != null) {
            id = bundle.getInt("id");

            cultureName = bundle.getString("cultureName");
            cultureImg = bundle.getInt("cultureImg");

            ((TextView)findViewById(R.id.culture_name)).setText(cultureName);

            ((ImageView)findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(cultureImg));
        }
        TextView txtGeo = (TextView) findViewById(R.id.txt_geo);
        txtGeo.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.btn_culture_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String land = inputLandArea.getText().toString();
        Intent i = new Intent(this, MainActivity.class);
        cultureInfo = Culture.getCultureInfo();
        cultures = Culture.getCultures();

        switch (id) {
            case R.id.imgBtnWheat: {
                wheat = Wheat.getInstance();
                wheat.setLandArea(land);
                manageCultures(cultures,wheat,land);
                break;
            }
            case R.id.imgBtnBarley: {
                barley = Barley.getInstance();
                barley.setLandArea(land);
                manageCultures(cultures,barley,land);
                break;
            }
            case R.id.imgBtnCorn: {
                corn = Corn.getInstance();
                corn.setLandArea(land);
                manageCultures(cultures,corn,land);
                break;
            }
            case R.id.imgBtnSoy: {
                soy=Soy.getInstance();
                soy.setLandArea(land);
                manageCultures(cultures,soy,land);
                break;
            }
            case R.id.imgBtnSunflower: {
                sunflower= Sunflower.getInstance();
                sunflower.setLandArea(land);
                manageCultures(cultures,sunflower,land);
                break;
            }
        }

        i.putExtra("cultureInfo", cultureInfo);
        i.putExtra("cultureSet", cultures);
        i.putExtra("id", id);
        i.putExtra("landArea", decorateWithUnits(land));
        startActivity(i);
    }

    private String decorateWithUnits(String str) {
        if (!(str.isEmpty()) && !(str.matches("^[0]*$"))) { str += " ha";  }
        else { str="";  }
        return str;
    }

    private void manageCultures(HashSet<Culture> cultures, Culture culture, String land){
        if ((!(land.isEmpty()) && !(land.matches("^[0]*$")))) {
            cultures.add(culture);
            cultureInfo.put(cultureName, cultureImg);
        } else {
            cultures.remove(culture);
            cultureInfo.remove(cultureName);
        }
    }
}
