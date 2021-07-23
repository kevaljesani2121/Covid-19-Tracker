package com.kpatel.covid19tracker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kpatel.covid19tracker.R;
import com.kpatel.covid19tracker.SingletonPattern.VolleySingleton;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCasesCounts, tvRecoveredCase, tvCriticalCase, tvActiveCase, tvTodayCases, tvTotalDeaths, tvTodayDeaths,
            tvAffectedCountries;
    PieChart piechart;
    ScrollView svStats, scrollViewAllItem;
    SimpleArcLoader loader;
    Button btnTrackCountries;
    SwipeRefreshLayout swipeLayout;
//

    String url = "https://corona.lmao.ninja/v2/all/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllIdFromMainActivity();
//
    }

    public void findAllIdFromMainActivity() {

        tvCasesCounts = findViewById(R.id.tvCasesCounts);
        tvRecoveredCase = findViewById(R.id.tvRecoveredCase);
        tvCriticalCase = findViewById(R.id.tvCriticalCase);
        tvActiveCase = findViewById(R.id.tvActiveCase);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);

        piechart = findViewById(R.id.piechart);
        svStats = findViewById(R.id.svStats);
        loader = findViewById(R.id.loader);

        btnTrackCountries = findViewById(R.id.btnTrackCountries);

        scrollViewAllItem = findViewById(R.id.scrollViewAllItem);

        fetchDataFromApi();

        btnTrackCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Track_countries.class);
                startActivity(intent);
            }
        });


        swipeLayout = findViewById(R.id.swipeLayout);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                url = "https://corona.lmao.ninja/v2/all/"; // your code
                swipeLayout.setRefreshing(false);
            }
        });
    }

    private void fetchDataFromApi() {

        String url = "https://corona.lmao.ninja/v2/all/";

        loader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    tvCasesCounts.setText(jsonObject.getString("cases"));
                    tvRecoveredCase.setText(jsonObject.getString("recovered"));
                    tvCriticalCase.setText(jsonObject.getString("critical"));
                    tvActiveCase.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                    piechart.addPieSlice(new PieModel("Cases", Integer.parseInt(tvCasesCounts.getText().toString()),
                            Color.parseColor("#FFA726")));
                    piechart.addPieSlice(new PieModel("Recovered", Integer.parseInt(tvRecoveredCase.getText().toString()),
                            Color.parseColor("#66BB6A")));
                    piechart.addPieSlice(new PieModel("Deaths", Integer.parseInt(tvTotalDeaths.getText().toString()),
                            Color.parseColor("#EF5350")));
                    piechart.addPieSlice(new PieModel("Active", Integer.parseInt(tvActiveCase.getText().toString()),
                            Color.parseColor("#29B6F6")));

                    piechart.startAnimation();

                    loader.stop();
                    loader.setVisibility(View.GONE);
                    svStats.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.stop();
                    loader.setVisibility(View.GONE);
                    svStats.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.stop();
                loader.setVisibility(View.GONE);
                svStats.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}