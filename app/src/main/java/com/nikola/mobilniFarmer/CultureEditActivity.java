package com.nikola.mobilniFarmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikola.culture.Barley;
import com.nikola.culture.Corn;
import com.nikola.culture.Soy;
import com.nikola.culture.Sunflower;
import com.nikola.culture.Wheat;

public class CultureEditActivity extends AppCompatActivity implements View.OnClickListener {

    Wheat wheat;
    Barley barley;
    Corn corn;
    Soy soy;
    Sunflower sunflower;
    int id = 0;
    EditText inputLandArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_edit);
        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            id = bundle.getInt("id");

            TextView name = (TextView) findViewById(R.id.culture_name);
            name.setText((String) bundle.get("cultureName"));

            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageDrawable(getResources().getDrawable((int) bundle.get("cultureImg")));
        }

        inputLandArea = (EditText) findViewById(R.id.input_land_area);

        TextView txtGeo = (TextView) findViewById(R.id.txt_geo);
        txtGeo.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.btn_culture_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String land = inputLandArea.getText().toString();
        Intent i = new Intent(this, MainActivity.class);

        switch (id) {
            case R.id.imgBtnWheat: {
                wheat.getInstance().setLandArea(land);
                break;
            }
            case R.id.imgBtnBarley: {
                barley.getInstance().setLandArea(land);
                break;
            }
            case R.id.imgBtnCorn: {
                corn.getInstance().setLandArea(land);
                break;
            }
            case R.id.imgBtnSoy: {
                soy.getInstance().setLandArea(land);
                break;
            }
            case R.id.imgBtnSunflower: {
                sunflower.getInstance().setLandArea(land);
                break;
            }
        }

        i.putExtra("id", id);
        i.putExtra("landArea", decorateWithUnits(land));
        startActivity(i);
    }

    private String decorateWithUnits(String str) {
        if (!(str.isEmpty())) {
            if (str.equals("0")) {
                str = "";
            } else {
                str += " ha";
            }
        }
        return str;
    }
}
