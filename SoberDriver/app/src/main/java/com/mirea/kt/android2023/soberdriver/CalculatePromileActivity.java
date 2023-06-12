package com.mirea.kt.android2023.soberdriver;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;



public class CalculatePromileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_promile);
        EditText editTextWeight = findViewById(R.id.etWeight);
        EditText editTextStrenght = findViewById(R.id.etStrenght);
        EditText editTextAmount = findViewById(R.id.etAmount);
        EditText editTextTime = findViewById(R.id.etTime);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int checkedIndex = radioGroup.indexOfChild(radioGroup);
        Button btnClc = findViewById(R.id.btnCalc);

        final int[] chosenGender = { 0 };
        btnClc.setOnClickListener(v -> {
            Log.i("calculate_per_mill_tag", "Click calc button");
            String weightStr = editTextWeight.getText().toString();
            if (weightStr.isEmpty()) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите ваш вес", Toast.LENGTH_SHORT).show();
                return;
            }
            int weightInt = Integer.parseInt(weightStr);
            if (weightInt < 40 || weightInt > 300) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите вес от 40 до 300 кг", Toast.LENGTH_SHORT).show();
                return;
            }
            String strenghtStr = editTextStrenght.getText().toString();
            if (strenghtStr.isEmpty()) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите крепость напитка", Toast.LENGTH_SHORT).show();
                return;
            }
            int strenghtInt = Integer.parseInt(strenghtStr);
            if (strenghtInt < 2 || strenghtInt > 100) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите крепость напитка от 2 до 200 %", Toast.LENGTH_SHORT).show();
                return;
            }
            String amountStr = editTextAmount.getText().toString();
            if (amountStr.isEmpty()) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите кол-во выпитого", Toast.LENGTH_SHORT).show();
                return;
            }
            int amountInt = Integer.parseInt(amountStr);
            if (amountInt <= 0) {
                Toast.makeText(CalculatePromileActivity.this, "Пожалуйста введите кол-во выпитого боьше 0", Toast.LENGTH_SHORT).show();
                return;
            }
            String timeStr = editTextTime.getText().toString();
            int timeInt = Integer.parseInt(timeStr);
            double strenght = strenghtInt * 0.01;
            double amountWater = weightInt * 0.7;
            double alcohol = amountInt * strenght;
            double pureAlcohol = alcohol * 0.79;
            double perMill = pureAlcohol / amountWater;
            double perMill1 = 0;
            if (chosenGender[0] == 0) {
                double time = timeInt * 0.15;
                perMill = perMill - time;
                Log.i("calculate_per_mill1_tag", "Result calculation: " + perMill);

            } else {
                double time = timeInt * 0.11;
                perMill = perMill - time;
                Log.i("calculate_per_mill1_tag", "Result calculation: " + perMill);
            }
            Log.i("calculate_per_mill1_tag", "Result calculation: " + perMill);
            if(perMill <= 0.3) {
                perMill1 = 0.3;
            }else {
                perMill1 = Math.round(perMill * 100) / 100.0;
            }
            Intent intent = new Intent(CalculatePromileActivity.this, ResultActivity.class);
            intent.putExtra("perMill", perMill1);
            startActivity(intent);
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radioButtonMan) {
                chosenGender[0] = 0;
            } else if (checkedId == R.id.radioButtonWomen) {
                chosenGender[0] = 1;
            }
        });
    }
}