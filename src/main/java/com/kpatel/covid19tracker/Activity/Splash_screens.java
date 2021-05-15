package com.kpatel.covid19tracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kpatel.covid19tracker.R;

public class Splash_screens extends AppCompatActivity {


    Button btnStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screens);

        findAllIdFromSplashActivity();
    }

    private void findAllIdFromSplashActivity() {
        btnStartActivity = findViewById(R.id.btnStartActivity);

        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash_screens.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}