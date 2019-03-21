package com.nikola.mobilniFarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_record);

        String culture = getIntent().getExtras().getString("culture");
        TextView text = (TextView)findViewById(R.id.bookTxt);
        text.setText(culture);
    }
}
