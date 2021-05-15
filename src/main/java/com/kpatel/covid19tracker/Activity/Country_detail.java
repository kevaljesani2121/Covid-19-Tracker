package com.kpatel.covid19tracker.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kpatel.covid19tracker.R;

public class Country_detail extends AppCompatActivity {

    TextView tvCountry, tvCase, tvTodayCase, tvDeaths, tvTodayDeaths, tvRecoveredCase, tvActiveCase, tvCriticalCase;
    ImageView ivCountryImage;
    ScrollView scCountryDetails;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        getSupportActionBar().setTitle("Details of Countries " + Track_countries.countryModelList.get(position).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findAllIdFromCountryDetails();

        setDataCountry();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void findAllIdFromCountryDetails() {
        tvCountry = findViewById(R.id.tvCountry);
        tvCase = findViewById(R.id.tvCase);
        tvTodayCase = findViewById(R.id.tvTodayCase);
        tvDeaths = findViewById(R.id.tvDeaths);
        tvRecoveredCase = findViewById(R.id.tvRecoveredCase);
        tvActiveCase = findViewById(R.id.tvActiveCase);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvCriticalCase = findViewById(R.id.tvCriticalCase);

        ivCountryImage = findViewById(R.id.ivCountryImage);

        scCountryDetails = findViewById(R.id.scCountryDetails);
    }

    @SuppressLint("CheckResult")
    public void setDataCountry() {

        Glide.with(this).load(Track_countries.countryModelList.get(position).getFlag()).into(ivCountryImage);

        tvCountry.setText(Track_countries.countryModelList.get(position).getCountry());
        tvCase.setText(Track_countries.countryModelList.get(position).getCases());
        tvTodayCase.setText(Track_countries.countryModelList.get(position).getTodayCases());
        tvDeaths.setText(Track_countries.countryModelList.get(position).getDeath());
        tvRecoveredCase.setText(Track_countries.countryModelList.get(position).getRecoveredCase());
        tvActiveCase.setText(Track_countries.countryModelList.get(position).getActiveCases());
        tvTodayDeaths.setText(Track_countries.countryModelList.get(position).getTodayDeath());
        tvCriticalCase.setText(Track_countries.countryModelList.get(position).getCriticalCases());
    }
}