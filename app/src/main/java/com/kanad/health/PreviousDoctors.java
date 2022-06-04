package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Objects;

public class PreviousDoctors extends AppCompatActivity {
    RecyclerView doctor_list_previous;
    ArrayList<previousdocs> list;
    PDoctorsAdapter pDoctorsAdapter;
    Animation scale_up;
    String uid;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_doctors);

        doctor_list_previous = findViewById(R.id.doctor_list_previous);
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up1);
        list = new ArrayList<>();
        doctor_list_previous.setLayoutManager(new LinearLayoutManager(PreviousDoctors.this));
        doctor_list_previous.setHasFixedSize(true);
        pDoctorsAdapter= new PDoctorsAdapter(list,this,PreviousDoctors.this);
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
        doctor_list_previous.setAdapter(pDoctorsAdapter);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            uid = FirebaseAuth.getInstance().getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(uid).child("PreviousDoctors");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        String uid = dataSnapshot.child("uid").getValue(String.class);
                        String date = dataSnapshot.child("date").getValue(String.class);
                        String district = dataSnapshot.child("district").getValue(String.class);
                        String state = dataSnapshot.child("state").getValue(String.class);
                        previousdocs previousDoctor = new previousdocs();
                        previousDoctor.setDate(date);
                        previousDoctor.setDistrict(district);
                        previousDoctor.setUid(uid);
                        previousDoctor.setState(state);
                        list.add(previousDoctor);
                    }
                    pDoctorsAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    if (list.size()==0){
                        progressDialog.dismiss();
                        LinearLayout empty2;
                        empty2 = findViewById(R.id.empty2);
                        empty2.setVisibility(View.VISIBLE);
                        doctor_list_previous.setVisibility(View.GONE);
                        ImageView seedoctors;
                        seedoctors =  findViewById(R.id.seedoctors);
                        seedoctors.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PreviousDoctors.this,DoctorShow.class);
                                startActivity(intent);
                            }
                        });
                        seedoctors.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                    seedoctors.startAnimation(scale_up);

                                }
                                return false;
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else{
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            uid = GoogleSignIn.getLastSignedInAccount(this).getId();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(uid).child("PreviousDoctors");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        String uid = dataSnapshot.child("uid").getValue(String.class);
                        String date = dataSnapshot.child("date").getValue(String.class);
                        String district = dataSnapshot.child("district").getValue(String.class);
                        String state = dataSnapshot.child("state").getValue(String.class);
                        previousdocs previousDoctor = new previousdocs();
                        previousDoctor.setDate(date);
                        previousDoctor.setDistrict(district);
                        previousDoctor.setUid(uid);
                        previousDoctor.setState(state);
                        list.add(previousDoctor);
                    }
                    if (list.size()==0){
                        LinearLayout empty2;
                        empty2 = findViewById(R.id.empty2);
                        empty2.setVisibility(View.VISIBLE);
                        doctor_list_previous.setVisibility(View.GONE);
                        ImageView seedoctors;
                        seedoctors =  findViewById(R.id.seedoctors);
                        seedoctors.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PreviousDoctors.this,DoctorShow.class);
                                startActivity(intent);
                            }
                        });
                        seedoctors.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                    seedoctors.startAnimation(scale_up);

                                }
                                return false;
                            }
                        });
                    }
                    pDoctorsAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}