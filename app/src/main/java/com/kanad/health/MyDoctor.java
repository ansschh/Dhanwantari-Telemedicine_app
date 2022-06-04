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
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDoctor extends AppCompatActivity {
    LinearLayout nobookmarked;
    ImageView adddoctors,back;
    Animation scale_up;
    RecyclerView mydoctorrecview;
    ArrayList<MyDoctorData> list;
    MyDoctorsAdapter myDoctorsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor);
        nobookmarked = findViewById(R.id.nobookmarked);
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up1);
        back = findViewById(R.id.back);
        adddoctors = findViewById(R.id.adddoctors);
        mydoctorrecview = findViewById(R.id.mydoctorrecview);
        adddoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDoctor.this,PreviousDoctors.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adddoctors.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    adddoctors.startAnimation(scale_up);

                }
                return false;
            }
        });
        ProgressDialog progressDialog = new ProgressDialog(MyDoctor.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        mydoctorrecview.setHasFixedSize(true);
        mydoctorrecview.setLayoutManager(new LinearLayoutManager(MyDoctor.this));
        list = new ArrayList<>();
        myDoctorsAdapter = new MyDoctorsAdapter(list,this);
        mydoctorrecview.setAdapter(myDoctorsAdapter);
        String uid;
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            uid = FirebaseAuth.getInstance().getUid();
        }else{
            uid = GoogleSignIn.getLastSignedInAccount(this).getId();
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegularDoctors").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MyDoctorData myDoctorData = new MyDoctorData();
                    myDoctorData.setUid(dataSnapshot.child("uid").getValue(String.class));
                    list.add(myDoctorData);
                    progressDialog.dismiss();
                }
                if (list.size()==0){
                    progressDialog.dismiss();
                    mydoctorrecview.setVisibility(View.GONE);
                    nobookmarked.setVisibility(View.VISIBLE);
                }
                myDoctorsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyDoctor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}