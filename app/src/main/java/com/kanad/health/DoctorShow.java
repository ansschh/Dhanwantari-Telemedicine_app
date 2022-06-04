package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorShow extends AppCompatActivity {
    RecyclerView doctor_list;
    String District;
    MyAdapter myAdapter;
    TextView doctorin;
    ArrayList<userData> list;
    TextView dummy_for_state,dummy_for_district;
    String State;
    LinearLayout empty1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show);
        doctor_list = findViewById(R.id.doctor_list);
        dummy_for_state = findViewById(R.id.dummy_for_state);
        empty1 = findViewById(R.id.empty1);
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
        doctorin = findViewById(R.id.doctorin);
        dummy_for_district = findViewById(R.id.dummy_for_district);
        ProgressDialog progressDialog = new ProgressDialog(DoctorShow.this,R.style.Theme_MaterialComponents_DayNight_Dialog_MinWidth_Bridge);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        if (GoogleSignIn.getLastSignedInAccount(DoctorShow.this) == null)
        {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    State = snapshot.child(uid).child("states").getValue(String.class);
                    District = snapshot.child(uid).child("districts").getValue(String.class);
                    doctorin.setText("Doctors in "+District);
                    doctor_list.setHasFixedSize(true);
                    doctor_list.setLayoutManager(new LinearLayoutManager(DoctorShow.this));
                    list = new ArrayList<>();
                    myAdapter = new MyAdapter(DoctorShow.this,list);
                    doctor_list.setAdapter(myAdapter);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                userData userData = dataSnapshot.child("EducationQualification").getValue(userData.class);
                                list.add(userData);
                            }
                            if (list.size()==0){
                                doctor_list.setVisibility(View.GONE);
                                empty1.setVisibility(View.VISIBLE);
                            }
                            myAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
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
        }else{
            String uid = GoogleSignIn.getLastSignedInAccount(DoctorShow.this).getId();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    State = snapshot.child(uid).child("states").getValue(String.class);
                    District = snapshot.child(uid).child("districts").getValue(String.class);
                    doctorin.setText("Doctors in "+District);
                    doctor_list.setHasFixedSize(true);
                    doctor_list.setLayoutManager(new LinearLayoutManager(DoctorShow.this));
                    list = new ArrayList<>();
                    myAdapter = new MyAdapter(DoctorShow.this,list);
                    doctor_list.setAdapter(myAdapter);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                userData userData = dataSnapshot.child("EducationQualification").getValue(userData.class);
                                list.add(userData);
                            }
                            myAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
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

    }
}