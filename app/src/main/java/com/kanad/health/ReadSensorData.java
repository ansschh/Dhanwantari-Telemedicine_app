package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.HealthDataTypes;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

import org.w3c.dom.Document;

public class ReadSensorData extends AppCompatActivity implements RemoteMonitoringAdapter.IUserRecycler {
    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 100;
    TextView textView2,googleapistatus,rpmstatus;
    FitnessOptions fitnessOptions;
    GoogleApiClient googleApiClient;
    String Name,Value;
    LinearLayout norpmrquests,rpmstarted;
    RemoteMonitoringAdapter remoteMonitoringAdapter;
    RecyclerView rpmreuqestrecview;
    ArrayList<RPMdata> list;
    Button stopsendingdata;
    RecyclerView healthdatarecyclerview;
    HealthDataAdapter healthDataAdapter;
    ArrayList<RHealthData> healthlist;
    private int mInterval = 10000;
    Boolean x = true;
    private Handler mHandler;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sensor_data);
        norpmrquests = findViewById(R.id.norpmrquests);
        healthdatarecyclerview = findViewById(R.id.healthdatarecyclerview);
        mHandler = new Handler();
//        Buttons
        rpmstarted = findViewById(R.id.rpmstarted);
        googleapistatus = findViewById(R.id.googleapistatus);
        rpmstatus = findViewById(R.id.rpmstatus);
        rpmreuqestrecview = findViewById(R.id.rpmreuqestrecview);
        stopsendingdata = findViewById(R.id.stopsendingdata);
        healthdatarecyclerview.setHasFixedSize(true);
        healthdatarecyclerview.setLayoutManager(new LinearLayoutManager(ReadSensorData.this));
        healthlist = new ArrayList<>();
        healthDataAdapter = new HealthDataAdapter(healthlist,ReadSensorData.this);
        healthdatarecyclerview.setAdapter(healthDataAdapter);
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
        if (GoogleSignIn.getLastSignedInAccount(ReadSensorData.this)!=null){
            DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests");
            databaseReference3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (getApplicationContext()!=null){
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String status = dataSnapshot.child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("status").getValue(String.class);
                            String uid = dataSnapshot.child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("uid").getValue(String.class);
                            if (status==null || uid==null || status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("rejected")){
                                rpmreuqestrecview.setVisibility(View.GONE);
                                norpmrquests.setVisibility(View.VISIBLE);
                            }else{
                                rpmreuqestrecview.setVisibility(View.VISIBLE);
                                norpmrquests.setVisibility(View.GONE);
                                if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                    ProgressDialog progressDialog = new ProgressDialog(ReadSensorData.this);
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setMessage("Loading");
                                    progressDialog.show();
                                    rpmreuqestrecview.setHasFixedSize(true);
                                    rpmreuqestrecview.setLayoutManager(new LinearLayoutManager(ReadSensorData.this));
                                    list = new ArrayList<>();
                                    remoteMonitoringAdapter = new RemoteMonitoringAdapter(list,ReadSensorData.this,ReadSensorData.this);
                                    rpmreuqestrecview.setAdapter(remoteMonitoringAdapter);
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests");
                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                                String status = dataSnapshot.child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("status").getValue(String.class);
                                                String uid = dataSnapshot.child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("uid").getValue(String.class);
                                                RPMdata rpMdata = new RPMdata();
                                                rpMdata.setUid(uid);
                                                rpMdata.setStatus(status);
                                                list.add(rpMdata);
                                            }
                                            if (list.size()==0){
                                                norpmrquests.setVisibility(View.VISIBLE);
                                                rpmreuqestrecview.setVisibility(View.GONE);
                                            }
                                            remoteMonitoringAdapter.notifyDataSetChanged();
                                            progressDialog.dismiss();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(ReadSensorData.this);
            builder.setMessage("Please, Sign in with your google account to continue as we use Google Fit API to monitor patient remotely");
            builder.setTitle("Sign in with google");
            builder.setCancelable(false);
            builder.setNegativeButton(
                    "Log Out",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            if (GoogleSignIn.getLastSignedInAccount(ReadSensorData.this) != null) {
                                GoogleSignInOptions gso = new GoogleSignInOptions.
                                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                        build();
                                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(ReadSensorData.this, gso);
                                googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(ReadSensorData.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ReadSensorData.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(ReadSensorData.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
            builder.setPositiveButton(
                    "Cancel",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    private void senddata(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DailyTotalResult step = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_STEP_COUNT_DELTA ).await(1, TimeUnit.MINUTES);
                showDataSet(step.getTotal());
                DailyTotalResult calories = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_CALORIES_EXPENDED ).await(1, TimeUnit.MINUTES);
                showDataSet(calories.getTotal());
                DailyTotalResult calories1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_CALORIES_EXPENDED ).await(1, TimeUnit.MINUTES);
                showDataSet(calories1.getTotal());
                DailyTotalResult weight = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_WEIGHT_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(weight.getTotal());
                DailyTotalResult weight1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_WEIGHT ).await(1, TimeUnit.MINUTES);
                showDataSet(weight1.getTotal());
                DailyTotalResult height = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_HEIGHT ).await(1, TimeUnit.MINUTES);
                showDataSet(height.getTotal());
                DailyTotalResult height1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_HEIGHT_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(height1.getTotal());
                DailyTotalResult glucose = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_BLOOD_GLUCOSE ).await(1, TimeUnit.MINUTES);
                showDataSet(glucose.getTotal());
                DailyTotalResult glucose1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(glucose1.getTotal());
                DailyTotalResult bloodpressure = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(bloodpressure.getTotal());
                DailyTotalResult bloodpressure1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_BLOOD_PRESSURE ).await(1, TimeUnit.MINUTES);
                showDataSet(bloodpressure1.getTotal());
                DailyTotalResult hydration = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_HYDRATION ).await(1, TimeUnit.MINUTES);
                showDataSet(hydration.getTotal());
                DailyTotalResult hydration1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_HYDRATION ).await(1, TimeUnit.MINUTES);
                showDataSet(hydration1.getTotal());
                DailyTotalResult heart = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_HEART_POINTS ).await(1, TimeUnit.MINUTES);
                showDataSet(heart.getTotal());
                DailyTotalResult heart1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_HEART_RATE_BPM ).await(1, TimeUnit.MINUTES);
                showDataSet(heart1.getTotal());
                DailyTotalResult heart2 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_HEART_POINTS ).await(1, TimeUnit.MINUTES);
                showDataSet(heart2.getTotal());
                DailyTotalResult heart3 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_HEART_RATE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(heart3.getTotal());
                DailyTotalResult matabolism = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_BASAL_METABOLIC_RATE ).await(1, TimeUnit.MINUTES);
                showDataSet(matabolism.getTotal());
                DailyTotalResult matabolism1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(matabolism1.getTotal());
                DailyTotalResult sleep = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_SLEEP_SEGMENT ).await(1, TimeUnit.MINUTES);
                showDataSet(sleep.getTotal());
                DailyTotalResult nutrition = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_NUTRITION ).await(1, TimeUnit.MINUTES);
                showDataSet(nutrition.getTotal());
                DailyTotalResult nutrition1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_NUTRITION_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(nutrition1.getTotal());
                DailyTotalResult bfp = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.TYPE_BODY_FAT_PERCENTAGE ).await(1, TimeUnit.MINUTES);
                showDataSet(bfp.getTotal());
                DailyTotalResult bfp1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(bfp1.getTotal());
                DailyTotalResult temprature = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_BODY_TEMPERATURE ).await(1, TimeUnit.MINUTES);
                showDataSet(temprature.getTotal());
                DailyTotalResult temprature1 = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY ).await(1, TimeUnit.MINUTES);
                showDataSet(temprature1.getTotal());
                DailyTotalResult mucus = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_CERVICAL_MUCUS ).await(1, TimeUnit.MINUTES);
                showDataSet(mucus.getTotal());
                DailyTotalResult menstruation = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_MENSTRUATION ).await(1, TimeUnit.MINUTES);
                showDataSet(menstruation.getTotal());
                DailyTotalResult cervical = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_CERVICAL_POSITION ).await(1, TimeUnit.MINUTES);
                showDataSet(cervical.getTotal());
                DailyTotalResult ovulation = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_OVULATION_TEST ).await(1, TimeUnit.MINUTES);
                showDataSet(ovulation.getTotal());
                DailyTotalResult vaginalspotting = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_VAGINAL_SPOTTING ).await(1, TimeUnit.MINUTES);
                showDataSet(vaginalspotting.getTotal());
                DailyTotalResult oxygen = Fitness.HistoryApi.readDailyTotal( googleApiClient, HealthDataTypes.TYPE_OXYGEN_SATURATION ).await(1, TimeUnit.MINUTES);
                showDataSet(oxygen.getTotal());
            }
        });
    }
    private void showDataSet(DataSet dataSet) {
        for (DataPoint dp : dataSet.getDataPoints()) {
            for(Field field : dp.getDataType().getFields()) {
                Log.e("History", field.getName() +": "+ dp.getValue(field));
                Name = field.getName();
                Value = dp.getValue(field).toString();
                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HealthData").child(FirebaseAuth.getInstance().getUid()).child("HealthData").child(Name);
                    HealthData healthData = new HealthData(Value,Name);
                    databaseReference.setValue(healthData);
                }else{
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HealthData").child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("HealthData").child(Name);
                    HealthData healthData = new HealthData(Value,Name);
                    databaseReference.setValue(healthData);
                }
            }
        }
    }
    @Override
    public void ChangeView(String uid) {
        rpmstarted.setVisibility(View.VISIBLE);
        if (GoogleSignIn.getLastSignedInAccount(ReadSensorData.this)!=null){
            googleApiClient = new GoogleApiClient.Builder(ReadSensorData.this)
                    .addApi(Fitness.HISTORY_API)
                    .enableAutoManage(ReadSensorData.this, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(ReadSensorData.this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addScope(Fitness.SCOPE_ACTIVITY_READ)
                    .addScope(Fitness.SCOPE_ACTIVITY_READ_WRITE)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {
                            googleapistatus.setText("Connected");
                            rpmstatus.setText("Started");
                            googleapistatus.setTextColor(Color.parseColor("#08a045"));
                            rpmstatus.setTextColor(Color.parseColor("#08a045"));
                            startRepeatingTask();
                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            Toast.makeText(ReadSensorData.this, "Cannot Connect", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(ReadSensorData.this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build();
            googleApiClient.connect();
            if (ContextCompat.checkSelfPermission(ReadSensorData.this, Manifest.permission.ACTIVITY_RECOGNITION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ReadSensorData.this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION},MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);
            }else if (ContextCompat.checkSelfPermission(ReadSensorData.this, Manifest.permission.ACTIVITY_RECOGNITION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ReadSensorData.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);
            }else if (ContextCompat.checkSelfPermission(ReadSensorData.this, Manifest.permission.ACTIVITY_RECOGNITION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ReadSensorData.this, new String[]{Manifest.permission.BODY_SENSORS},MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);
            }
            fitnessOptions = FitnessOptions.builder()
                    .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_WEIGHT, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_WEIGHT_SUMMARY, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_HEIGHT_SUMMARY, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_HYDRATION, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_HEART_POINTS, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_POWER_SAMPLE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.AGGREGATE_HEART_POINTS, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_WORKOUT_EXERCISE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_STEP_COUNT_CADENCE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_SLEEP_SEGMENT, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_NUTRITION, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_LOCATION_SAMPLE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_HYDRATION, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_CYCLING_WHEEL_RPM, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_CYCLING_WHEEL_REVOLUTION, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_BASAL_METABOLIC_RATE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_BODY_FAT_PERCENTAGE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_CYCLING_PEDALING_CADENCE, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_MOVE_MINUTES, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_SPEED, FitnessOptions.ACCESS_READ)
                    .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE, FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_BLOOD_GLUCOSE,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_BLOOD_PRESSURE,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_BODY_TEMPERATURE,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_CERVICAL_MUCUS,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_MENSTRUATION,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_CERVICAL_POSITION,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_OVULATION_TEST,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_VAGINAL_SPOTTING,FitnessOptions.ACCESS_READ)
                    .addDataType(HealthDataTypes.TYPE_OXYGEN_SATURATION,FitnessOptions.ACCESS_READ)
                    .build();
            GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(ReadSensorData.this, fitnessOptions);
            if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
                GoogleSignIn.requestPermissions(
                        ReadSensorData.this, // your activity
                        MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION,
                        account,
                        fitnessOptions);
            } else {
                stopsendingdata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(ReadSensorData.this);
                        builder.setMessage("Do you really want to stop this remote patient monitoring session, by doing this we will stop sending data to your doctor.");
                        builder.setTitle("Are Your Sure");
                        builder.setCancelable(false);
                        builder.setNegativeButton(
                                "Yes",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();
                                        String uid1;
                                        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                            uid1 = FirebaseAuth.getInstance().getUid();
                                        }else{
                                            uid1 = GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId();
                                        }
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(uid1).child("status");
                                        databaseReference.setValue("stopped").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(ReadSensorData.this, "Remote Patient Monitoring Stopped", Toast.LENGTH_SHORT).show();
                                                googleapistatus.setText("Stopped");
                                                rpmstatus.setText("Stopped");
                                                googleapistatus.setTextColor(Color.parseColor("#f6404F"));
                                                rpmstatus.setTextColor(Color.parseColor("#f6404F"));
                                                x = false;
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ReadSensorData.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                        builder.setPositiveButton(
                                "No",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HealthData").child(GoogleSignIn.getLastSignedInAccount(ReadSensorData.this).getId()).child("HealthData");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (getApplicationContext()!=null){
                            ProgressDialog progressDialog = new ProgressDialog(ReadSensorData.this);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.setMessage("Loading");
                            progressDialog.show();
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                RHealthData rHealthData = new RHealthData();
                                rHealthData.setName(dataSnapshot.child("name").getValue(String.class));
                                rHealthData.setValue(dataSnapshot.child("value").getValue(String.class));
                                healthlist.add(rHealthData);
                            }
                            if (list.size()==0){
                                AlertDialog.Builder builder
                                        = new AlertDialog
                                        .Builder(ReadSensorData.this);
                                builder.setMessage("There is some error with your Google fitness environment, because there are no fitness data to send to your doctor.");
                                builder.setTitle("No data found to send");
                                builder.setCancelable(false);
                                builder.setNegativeButton(
                                        "OK",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                            healthDataAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ReadSensorData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(ReadSensorData.this);
            builder.setMessage("Please, Sign in with your google account to continue as we use Google Fit API to monitor patient remotely");
            builder.setTitle("Sign in with google");
            builder.setCancelable(false);
            builder.setNegativeButton(
                    "Log Out",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            if (GoogleSignIn.getLastSignedInAccount(ReadSensorData.this) != null) {
                                GoogleSignInOptions gso = new GoogleSignInOptions.
                                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                        build();
                                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(ReadSensorData.this, gso);
                                googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(ReadSensorData.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ReadSensorData.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(ReadSensorData.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
            builder.setPositiveButton(
                    "Cancel",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                senddata();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask(){
        mStatusChecker.run();
    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
}