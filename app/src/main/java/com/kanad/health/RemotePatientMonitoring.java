package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemotePatientMonitoring extends AppCompatActivity {
    RecyclerView doctorsiderpm;
    ArrayList<RHealthData> list;
    HealthDataAdapter adapter;
    Button stoprpm,calltopatient,sendalert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_patient_monitoring);
        stoprpm = findViewById(R.id.stoprpm);
        calltopatient = findViewById(R.id.calltopatient);
        sendalert = findViewById(R.id.sendalert);
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
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String uid;
            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                uid = FirebaseAuth.getInstance().getUid();
            }else{
                uid = GoogleSignIn.getLastSignedInAccount(this).getId();
            }
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(bundle.getString("uid"));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("stopped")){
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(bundle.getString("uid"));
                        databaseReference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (getApplicationContext()!=null){
                                    String name = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                    AlertDialog.Builder builder
                                            = new AlertDialog
                                            .Builder(RemotePatientMonitoring.this);
                                    builder.setMessage(name+" has stopped sending data from their end please contact "+name+" to start remote patient monitoring again");
                                    builder.setTitle("RPM Stopped");
                                    builder.setCancelable(false);
                                    builder.setNegativeButton(
                                            "Ok",
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
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(RemotePatientMonitoring.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        list = new ArrayList<>();
                        doctorsiderpm = findViewById(R.id.doctorsiderpm);
                        adapter = new HealthDataAdapter(list,RemotePatientMonitoring.this);
                        doctorsiderpm.setHasFixedSize(true);
                        doctorsiderpm.setLayoutManager(new LinearLayoutManager(RemotePatientMonitoring.this));
                        doctorsiderpm.setAdapter(adapter);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HealthData").child(bundle.getString("uid")).child("HealthData");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (getApplicationContext()!=null){
                                    ProgressDialog progressDialog = new ProgressDialog(RemotePatientMonitoring.this);
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setMessage("Loading");
                                    progressDialog.show();
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                        RHealthData rHealthData = new RHealthData();
                                        rHealthData.setName(dataSnapshot.child("name").getValue(String.class));
                                        rHealthData.setValue(dataSnapshot.child("value").getValue(String.class));
                                        list.add(rHealthData);
                                    }
                                    if (list.size()==0){
                                        progressDialog.dismiss();
                                        android.app.AlertDialog.Builder builder
                                                = new android.app.AlertDialog
                                                .Builder(RemotePatientMonitoring.this);
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
                                        android.app.AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                    adapter.notifyDataSetChanged();
                                    progressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(RemotePatientMonitoring.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        stoprpm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                android.app.AlertDialog.Builder builder
                                        = new android.app.AlertDialog
                                        .Builder(RemotePatientMonitoring.this);
                                builder.setMessage("Do you really want to stop the on going remote patient monitoring session.");
                                builder.setTitle("Are You Sure?");
                                builder.setCancelable(false);
                                builder.setNegativeButton(
                                        "Yes",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                dialog.cancel();
                                                String uid;
                                                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                    uid = FirebaseAuth.getInstance().getUid();
                                                }else{
                                                    uid = GoogleSignIn.getLastSignedInAccount(RemotePatientMonitoring.this).getId();
                                                }
                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(bundle.getString("uid")).child("status");
                                                databaseReference1.setValue("stopped").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(RemotePatientMonitoring.this, "Session Stopped", Toast.LENGTH_SHORT).show();
                                                        finish();
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
                                android.app.AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });
                        calltopatient.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(bundle.getString("uid"));
                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String phonenumber = "+91 "+snapshot.child("mobilenumber").getValue(String.class);
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonenumber));
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                        sendalert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(bundle.getString("uid")).child("token");
                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String uid1;
                                        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                            uid1 = FirebaseAuth.getInstance().getUid();
                                        }else{
                                            uid1 = GoogleSignIn.getLastSignedInAccount(RemotePatientMonitoring.this).getId();
                                        }
                                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Users").child(uid1);
                                        databaseReference2.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String name = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                AlertDialog.Builder alert = new AlertDialog.Builder(RemotePatientMonitoring.this);
                                                final EditText edittext = new EditText(RemotePatientMonitoring.this);
                                                alert.setMessage("Write your message below.");
                                                alert.setTitle("Message");
                                                alert.setView(edittext);
                                                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int whichButton) {
                                                        String YouEditTextValue = edittext.getText().toString();
                                                        if (!YouEditTextValue.isEmpty()){
                                                            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(snapshot.child("token").getValue(String.class),"Message from "+name+":",name+":\n\n"+edittext.getText().toString(),RemotePatientMonitoring.this,RemotePatientMonitoring.this,"Alert",uid);
                                                            notificationsSender.SendNotifications();
                                                        }
                                                    }
                                                });
                                                alert.show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RemotePatientMonitoring.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}