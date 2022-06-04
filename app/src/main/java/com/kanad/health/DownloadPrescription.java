package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DownloadPrescription extends AppCompatActivity {
    String uid;
    RecyclerView rcvfordownloadlink;
    DownloadAdapter downloadAdapter;
    ArrayList<DownloadLink> list;
    Animation scale_up;
    LinearLayout empty3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_prescription);
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up1);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        empty3 = findViewById(R.id.empty3);
        rcvfordownloadlink = findViewById(R.id.rcvfordownloadlink);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }else{
            uid = GoogleSignIn.getLastSignedInAccount(DownloadPrescription.this).getId();
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Prescriptions").child(uid).child("Prescriptions");
        rcvfordownloadlink.setHasFixedSize(true);
        rcvfordownloadlink.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        downloadAdapter = new DownloadAdapter(this,list);
        rcvfordownloadlink.setAdapter(downloadAdapter);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    DownloadLink downloadLink = dataSnapshot.getValue(DownloadLink.class);
                    list.add(downloadLink);
                }
                downloadAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                if (list.size()<=0){
                    empty3.setVisibility(View.VISIBLE);
                    rcvfordownloadlink.setVisibility(View.GONE);
                    ImageView seedoctors___;
                    seedoctors___ = findViewById(R.id.seedoctors___);
                    seedoctors___.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DownloadPrescription.this,DoctorShow.class);
                            startActivity(intent);
                        }
                    });
                    seedoctors___.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                seedoctors___.startAnimation(scale_up);

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

        ImageView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (Throwable throwable) {
                    Toast.makeText(DownloadPrescription.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                }
            }
        });

    }
}