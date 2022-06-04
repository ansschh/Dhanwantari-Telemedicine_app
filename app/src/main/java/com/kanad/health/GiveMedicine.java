package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiveMedicine extends AppCompatActivity {
    RecyclerView rcylcviewformedicine;
    PatientAdapter patientAdapter;
    ArrayList<PatientData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_medicine);
        rcylcviewformedicine = findViewById(R.id.rcylcviewformedicine);
        ProgressDialog progressDialog = new ProgressDialog(GiveMedicine.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
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
        progressDialog.show();
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            if (bundle.getString("From")!=null){
                Snackbar snackbar = Snackbar.make(GiveMedicine.this,findViewById(android.R.id.content),"Click On Any Patient To Send Them Prescription",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else{
//            Do Nothing
            }
        }
        if (GoogleSignIn.getLastSignedInAccount(GiveMedicine.this) == null)
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String user_uid = user.getUid().toString();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String State = snapshot.child(user_uid).child("states").getValue(String.class);
                    String District = snapshot.child(user_uid).child("districts").getValue(String.class);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(user_uid).child("PreviousPatients");
                    rcylcviewformedicine.setHasFixedSize(true);
                    rcylcviewformedicine.setLayoutManager(new LinearLayoutManager(GiveMedicine.this));
                    list = new ArrayList<>();
                    patientAdapter = new PatientAdapter(GiveMedicine.this,list);
                    rcylcviewformedicine.setAdapter(patientAdapter);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                PatientData patientData = dataSnapshot.getValue(PatientData.class);
                                list.add(patientData);
                            }
                            patientAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(GiveMedicine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(GiveMedicine.this);
            String user_id_google_profile = GoogleSignIn.getLastSignedInAccount(GiveMedicine.this).getId().toString();
            if (acct != null) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String State = snapshot.child(user_id_google_profile).child("states").getValue(String.class);
                        String District = snapshot.child(user_id_google_profile).child("districts").getValue(String.class);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(user_id_google_profile).child("PreviousPatients");
                        rcylcviewformedicine.setHasFixedSize(true);
                        rcylcviewformedicine.setLayoutManager(new LinearLayoutManager(GiveMedicine.this));
                        list = new ArrayList<>();
                        patientAdapter = new PatientAdapter(GiveMedicine.this,list);
                        rcylcviewformedicine.setAdapter(patientAdapter);
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    PatientData patientData = dataSnapshot.getValue(PatientData.class);
                                    list.add(patientData);
                                }
                                patientAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(GiveMedicine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
}