package com.kpatel.covid19tracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kpatel.covid19tracker.Adapter.CountryAdapter;
import com.kpatel.covid19tracker.Model.CountryModel;
import com.kpatel.covid19tracker.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Track_countries extends AppCompatActivity {

    public static ArrayList<CountryModel> countryModelList = new ArrayList<>();
    public static ArrayList<CountryModel> tempCountryModelList = new ArrayList<>();
    EditText etSearchCountries;
    SimpleArcLoader loader;
    RecyclerView lvCountries;
    CountryModel countryModel;
    CountryAdapter countryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_countries);

        findAllIdFromTrackCountries();

        fetchDataFromApi();

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchCountries();
    }

    private void fetchDataFromApi() {

        String url = "https://corona.lmao.ninja/v2/countries/";

        loader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagUrl = object.getString("flag");

                        countryModel = new CountryModel(flagUrl, countryName, cases, todayCases, deaths, todayDeaths, recovered
                                , active, critical);
                        countryModelList.add(countryModel);

                    }

                    lvCountries.setLayoutManager(new GridLayoutManager(Track_countries.this, 1));
                    countryModelList.add(countryModel);
                    tempCountryModelList.addAll(countryModelList);
                    countryAdapter = new CountryAdapter(Track_countries.this, tempCountryModelList, new CountryAdapter.OnViewClickListener() {

                        @Override
                        public void OnItemClick(int position) {
                            Intent intent = new Intent(Track_countries.this, Country_detail.class);
                            intent.putExtra("position", position);
                            startActivity(intent);
                            finish();
                        }
                    });
//
                    lvCountries.setAdapter(countryAdapter);

                    loader.stop();
                    loader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.stop();
                    loader.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loader.stop();
                loader.setVisibility(View.GONE);
                Toast.makeText(Track_countries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    void resetAdapter() {
        if (countryAdapter != null) {
            countryAdapter.notifyDataSetChanged();
        }
    }

    void searchCountries() {
        etSearchCountries.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                countryModelList.clear();
                countryAdapter.getFilter().filter(charSequence);
                countryAdapter.notifyDataSetChanged();
//
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void findAllIdFromTrackCountries() {

        etSearchCountries = findViewById(R.id.etSearchCountries);
        lvCountries = findViewById(R.id.lvCountries);
        loader = findViewById(R.id.loader);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}