package com.mirea.kt.android2023.soberdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity started");
        Button btnLogIn = findViewById(R.id.btnLog);

        btnLogIn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Log In Button Pressed");
            EditText etLogin = findViewById(R.id.etLogin);
            EditText etPassword = findViewById(R.id.etPassword);


            HashMap<String, String> requestBody = new HashMap<>();
            requestBody.put("lgn", etLogin.getText().toString());
            requestBody.put("pwd", etPassword.getText().toString());
            requestBody.put("g", "RIBO-04-21");

            HTTPRequestRunnable httpPOSTRunnable = new HTTPRequestRunnable
                    ("POST", "https://android-for-students.ru/coursework/login.php", requestBody);
            Thread th = new Thread(httpPOSTRunnable);
            th.start();
            try {
                th.join();
                Log.d(TAG, "onCreate: " + httpPOSTRunnable.getResponseBody());
            } catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
            try {
                JSONObject jsonResponse = new JSONObject(httpPOSTRunnable.getResponseBody());
                int resultCode = jsonResponse.getInt("result_code");
                if (resultCode == 1) {
                    Intent actIntent = new Intent(getApplicationContext(), CalculatePromileActivity.class);
                    startActivity(actIntent);
                } else {
                    Toast.makeText(this, "Неправильно введены данные", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });
    }
}