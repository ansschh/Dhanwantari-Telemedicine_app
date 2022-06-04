package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyPatient extends AppCompatActivity {
    RecyclerView mypatient_view;
    ArrayList<MyPatientUid> list;
    MyPatientAdapter myPatientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient);
        mypatient_view = findViewById(R.id.mypatient_view);
        mypatient_view.setHasFixedSize(true);
        mypatient_view.setLayoutManager(new LinearLayoutManager(MyPatient.this));
        list = new ArrayList<>();
        myPatientAdapter = new MyPatientAdapter(list,this);
        mypatient_view.setAdapter(myPatientAdapter);
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
        String uid;
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            uid = FirebaseAuth.getInstance().getUid();
        }else{
            uid = GoogleSignIn.getLastSignedInAccount(this).getId();
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegularPatients").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MyPatientUid myPatientUid = new MyPatientUid();
                    myPatientUid.setUid(dataSnapshot.child("uid").getValue(String.class));
                    list.add(myPatientUid);
                }
                myPatientAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}