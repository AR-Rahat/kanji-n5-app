package com.example.kanji_n5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Slogan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogan);


        new Handler().postDelayed(() -> {
            // Start your main activity here
            Intent nextIntent = new Intent(Slogan.this, Homepage.class);
            startActivity(nextIntent);
            finish();
        }, 3000);
    }
}