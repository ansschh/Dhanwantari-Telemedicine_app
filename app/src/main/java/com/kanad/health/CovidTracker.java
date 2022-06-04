package com.kanad.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kanad.health.api.ApiUtilities;
import com.kanad.health.api.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidTracker extends AppCompatActivity {
    TextView totalconfirmed,todayconfirmed,totalactive,updateddate,totalrecovered,todayrecovered_,totaldeaths,todaysdeath,totaltests;
    private List<CountryData> list;
    PieChart pie_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tracker);
        totalconfirmed = findViewById(R.id.totalconfirmed);
        todayconfirmed = findViewById(R.id.todayconfirmed);
        totalactive = findViewById(R.id.totalactive);
        totalrecovered = findViewById(R.id.totalrecovered);
        todayrecovered_ = findViewById(R.id.todayrecovered);
        totaldeaths = findViewById(R.id.totaldeaths);
        updateddate = findViewById(R.id.updateddate);
        todaysdeath = findViewById(R.id.todaysdeath);
        ImageView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        totaltests = findViewById(R.id.totaltests);
        pie_chart = findViewById(R.id.pie_chart);
        list = new ArrayList<>();
        ProgressDialog progressDialog = new ProgressDialog(CovidTracker.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                        if (!(response.body() ==null)){
                            list.addAll(response.body());
                            for (int i = 0; i<list.size(); i++){
                                if (list.get(i).getCountry().equalsIgnoreCase("India")){
                                    int confirm = Integer.valueOf(list.get(i).getCases());
                                    int active = Integer.valueOf(list.get(i).getActive());
                                    int recovered = Integer.valueOf(list.get(i).getRecovered());
                                    int death = Integer.valueOf(list.get(i).getDeaths());
                                    int todayConfirm = Integer.valueOf(list.get(i).getTodayCases());
                                    int todaydeath = Integer.valueOf(list.get(i).getTodayDeaths());
                                    int todayrecovered = Integer.valueOf(list.get(i).getTodayRecovered());
                                    int tests = Integer.valueOf(list.get(i).getTests());

                                    totalconfirmed.setText(NumberFormat.getInstance().format(confirm));
                                    todayconfirmed.setText(NumberFormat.getInstance().format(todayConfirm));
                                    totalactive.setText(NumberFormat.getInstance().format(active));
                                    totalrecovered.setText(NumberFormat.getInstance().format(recovered));
                                    totaldeaths.setText(NumberFormat.getInstance().format(death));
                                    todaysdeath.setText(NumberFormat.getInstance().format(todaydeath));
                                    todayrecovered_.setText(NumberFormat.getInstance().format(todayrecovered));
                                    totaltests.setText(NumberFormat.getInstance().format(tests));

                                    setext(list.get(i).getUpdated());

                                    pie_chart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                                    pie_chart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.bluepie)));
                                    pie_chart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.greenpie)));
                                    pie_chart.addPieSlice(new PieModel("Death",death,getResources().getColor(R.color.redpie)));
                                    progressDialog.dismiss();
                                }
                            }
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(CovidTracker.this, "Data Not Available Try After Sometime", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(CovidTracker.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setext(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long millisecond = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        updateddate.setText("Updated at "+format.format(calendar.getTime()));
    }
}