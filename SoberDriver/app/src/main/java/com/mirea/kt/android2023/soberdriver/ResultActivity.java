package com.mirea.kt.android2023.soberdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class ResultActivity extends AppCompatActivity {

    private TextView etProm;
    private ImageView etIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        etProm = findViewById(R.id.etProm);
        etIm = findViewById(R.id.etIm);

        Intent intent = getIntent();
        double perMill = intent.getDoubleExtra("perMill", 0);

        etProm.setText(String.valueOf(perMill));

        if (perMill >= 0.3 && perMill < 1.5) {
            etProm.setTextColor(Color.GREEN);
            etIm.setImageResource(R.drawable.drunk_photos_01);
        } else if (perMill >= 1.5 && perMill <= 2.5) {
            etProm.setTextColor(Color.YELLOW);
            etIm.setImageResource(R.drawable.drunk_photos_02);
        } else if(perMill >= 2.5){
            etProm.setTextColor(Color.RED);
            etIm.setImageResource(R.drawable.drunk_photos_03);
        }
    }
}