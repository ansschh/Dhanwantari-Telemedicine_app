package com.kanad.health;

import static android.graphics.Color.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String uid;
    String name, VerificationStatus, lastname;
    TextView nametv, nametv_doctor, verificationStatus, applicationStatus, spelizationHome;
    Animation scale_up;
    String STATES, DISTRICT,Application_Status;
    ScrollView patient_scrollView, doctor_scrollView;
    ImageView contactus,privacypolicy,privacypolicy1,mydoctors, profile,booked_slot,previousdoctors, seedoctor, logout,covidcheck,sendinfo,checkdisease, govschemes, seeprescription, joinmeeting;
    ImageView contactus1,mypatient,mypatientrequest,profile_doctor, logout_doctor, previouspatient_doctor, editVerification_doctor, joinmeeting_doctor, govschemes_doctor, sentprescription_doctor, change_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up1);
        spelizationHome = findViewById(R.id.spelizationHome);
        nametv = findViewById(R.id.nametv);
        booked_slot = findViewById(R.id.booked_slot);
        joinmeeting_doctor = findViewById(R.id.joinmeeting_doctor);
        mypatientrequest = findViewById(R.id.mypatientrequest);
        mypatient = findViewById(R.id.mypatient);
        contactus = findViewById(R.id.contactus);
        contactus1 = findViewById(R.id.contactus1);
        privacypolicy = findViewById(R.id.privacypolicy);
        privacypolicy1 = findViewById(R.id.privacypolicy1);
        sendinfo = findViewById(R.id.sendinfo);
        mydoctors = findViewById(R.id.mydoctors);
        previousdoctors = findViewById(R.id.previousdoctors);
        covidcheck = findViewById(R.id.covidcheck);
        profile_doctor = findViewById(R.id.profile_doctor);
        editVerification_doctor = findViewById(R.id.editVerification_doctor);
        govschemes_doctor = findViewById(R.id.govschemes_doctor);
        previouspatient_doctor = findViewById(R.id.previouspatient_doctor);
        doctor_scrollView = findViewById(R.id.doctor_scrollView);
        sentprescription_doctor = findViewById(R.id.sentprescription_doctor);
        nametv_doctor = findViewById(R.id.nametv_doctor);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);
        change_schedule = findViewById(R.id.change_schedule);
        verificationStatus = findViewById(R.id.verificationStatus);
        applicationStatus = findViewById(R.id.applicationStatus);
        seedoctor = findViewById(R.id.seedoctor);
        logout_doctor = findViewById(R.id.logout_doctor);
        checkdisease = findViewById(R.id.checkdisease);
        patient_scrollView = findViewById(R.id.patient_scrollView);
        govschemes = findViewById(R.id.govschemes);
        seeprescription = findViewById(R.id.seeprescription);
        joinmeeting = findViewById(R.id.joinmeeting);
        ArrayList<previousdocs> list;
        list = new ArrayList<>();
        PDoctorsAdapter pDoctorsAdapter= new PDoctorsAdapter(list,this,MainActivity.this);
        pDoctorsAdapter.notifyDataSetChanged();
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.dismiss();
        if (FirebaseAuth.getInstance().getCurrentUser() == null && GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            ProgressDialog progressDialog1 = new ProgressDialog(MainActivity.this);
            progressDialog1.setMessage("Loading");
            progressDialog1.setCanceledOnTouchOutside(false);
            progressDialog1.show();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                progressDialog.show();
                uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (uid!=null){
                            progressDialog.dismiss();
                            ProgressDialog progressDialog2 = new ProgressDialog(MainActivity.this);
                            progressDialog2.setMessage("Loading");
                            progressDialog2.setCanceledOnTouchOutside(false);
                            progressDialog2.show();
                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                            nametv.setText("Hi, " + name);
                            STATES = snapshot.child(uid).child("states").getValue(String.class);
                            DISTRICT = snapshot.child(uid).child("districts").getValue(String.class);
                            if (STATES != null && DISTRICT != null) {
                                progressDialog.dismiss();
                                if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                    progressDialog.dismiss();
                                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(STATES).child(DISTRICT).child(uid);
                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Application_Status = snapshot.child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                            VerificationStatus = snapshot.child("EducationQualification").child("approved").getValue(String.class);
                                            spelizationHome.setText(snapshot.child("EducationQualification").child("specilization_").getValue(String.class));
                                            if (VerificationStatus!=null && spelizationHome!=null){
                                                if (VerificationStatus.equalsIgnoreCase("true")) {
                                                    progressDialog2.dismiss();
                                                    progressDialog1.dismiss();
                                                    progressDialog.dismiss();
                                                    verificationStatus.setText("Approved !");
                                                } else if (VerificationStatus.equalsIgnoreCase("false")) {
                                                    progressDialog2.dismiss();
                                                    progressDialog1.dismiss();
                                                    progressDialog.dismiss();
                                                    // your code above
                                                    final Dialog d = new Dialog(MainActivity.this);
                                                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    d.setContentView(R.layout.dialog_layout);
                                                    // Thank you Button Listener.
                                                    final TextView tv = (TextView) d.findViewById(R.id.textView1);
                                                    final TextView thankyou = (TextView) d.findViewById(R.id.thankyou);
                                                    d.setCancelable(false);
                                                    d.setCanceledOnTouchOutside(false);
                                                    thankyou.setOnTouchListener(new View.OnTouchListener() {
                                                        @Override
                                                        public boolean onTouch(View arg0, MotionEvent event) {
                                                            // TODO Auto-generated method stub
                                                            finish();
                                                            return true;
                                                        }

                                                    });

                                                    d.show();
                                                    patient_scrollView.setVisibility(View.GONE);
                                                    doctor_scrollView.setVisibility(View.GONE);
                                                    verificationStatus.setText("Under Review");
                                                    verificationStatus.setTextColor(Color.parseColor("#FFE31F"));
                                                }else{
                                                    Toast.makeText(MainActivity.this, "Please complete your profile", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            if (Application_Status != null) {
                                                progressDialog2.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                patient_scrollView.setVisibility(View.GONE);
                                                doctor_scrollView.setVisibility(View.VISIBLE);
                                                if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                    uid = FirebaseAuth.getInstance().getUid();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                            nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                } else {
                                                    progressDialog2.dismiss();
                                                    progressDialog1.dismiss();
                                                    progressDialog.dismiss();
                                                    uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            } else {
                                                progressDialog2.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                patient_scrollView.setVisibility(View.VISIBLE);
                                                doctor_scrollView.setVisibility(View.GONE);
                                                if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                    uid = FirebaseAuth.getInstance().getUid();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                            nametv.setText("Hello, " + name);
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                } else {
                                                    progressDialog2.dismiss();
                                                    progressDialog1.dismiss();
                                                    progressDialog.dismiss();
                                                    uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            nametv.setText("Hello, " + name);
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog2.dismiss();
                                            progressDialog1.dismiss();
                                            progressDialog.dismiss();
                                        }
                                    });
                                }else
                                {
                                    ProgressDialog progressDialog3 = new ProgressDialog(MainActivity.this);
                                    progressDialog3.setCanceledOnTouchOutside(false);
                                    progressDialog3.setMessage("Loading");
                                    progressDialog3.show();
                                    uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Application_Status = snapshot.child(STATES).child(DISTRICT).child(uid).child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                            VerificationStatus = snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class);
                                            if (VerificationStatus.equalsIgnoreCase("true")) {
                                                progressDialog3.dismiss();
                                                progressDialog2.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                verificationStatus.setText("Approved !");
                                            } else if (VerificationStatus.equalsIgnoreCase("false")) {
                                                progressDialog3.dismiss();
                                                progressDialog2.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                verificationStatus.setText("Under Review");
                                                verificationStatus.setTextColor(Color.parseColor("#FFE31F"));
                                            }
                                            spelizationHome.setText(snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                            if (Application_Status != null) {
                                                progressDialog2.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                progressDialog3.dismiss();
                                                patient_scrollView.setVisibility(View.GONE);
                                                doctor_scrollView.setVisibility(View.VISIBLE);
                                                if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                    progressDialog3.dismiss();
                                                    progressDialog2.dismiss();
                                                    progressDialog1.dismiss();
                                                    progressDialog.dismiss();
                                                    uid = FirebaseAuth.getInstance().getUid();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                            nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                } else {
                                                    uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            progressDialog3.dismiss();
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                            progressDialog3.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                            progressDialog3.dismiss();
                                                        }
                                                    });
                                                }
                                            } else {
                                                progressDialog1.dismiss();
                                                progressDialog3.dismiss();
                                                patient_scrollView.setVisibility(View.VISIBLE);
                                                doctor_scrollView.setVisibility(View.GONE);
                                                if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                    uid = FirebaseAuth.getInstance().getUid();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                            nametv.setText("Hello, " + name);
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                            progressDialog3.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                } else {
                                                    uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                            nametv.setText("Hello, " + name);
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                            progressDialog3.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            progressDialog.dismiss();
                                                            progressDialog2.dismiss();
                                                            progressDialog1.dismiss();
                                                            progressDialog.dismiss();
                                                            progressDialog3.dismiss();
                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            progressDialog2.dismiss();
                                            progressDialog1.dismiss();
                                            progressDialog.dismiss();
                                            progressDialog3.dismiss();
                                        }
                                    });
                                }

                            }else{
                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                progressDialog2.dismiss();
                                progressDialog1.dismiss();
                                progressDialog.dismiss();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            progressDialog1.dismiss();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                        progressDialog1.dismiss();
                        progressDialog.dismiss();
                    }
                });
            }else
            {
                progressDialog1.dismiss();
                ProgressDialog progressDialog3 = new ProgressDialog(MainActivity.this);
                progressDialog3.setCanceledOnTouchOutside(false);
                progressDialog3.setMessage("Loading");
                progressDialog3.show();
                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                        nametv.setText("Hi, " + name);
                        STATES = snapshot.child(uid).child("states").getValue(String.class);
                        DISTRICT = snapshot.child(uid).child("districts").getValue(String.class);
                        if (STATES != null && DISTRICT != null) {
                            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                uid = FirebaseAuth.getInstance().getUid();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Application_Status = snapshot.child(STATES).child(DISTRICT).child(uid).child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                        VerificationStatus = snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class);
                                        if (VerificationStatus.equalsIgnoreCase("true")) {
                                            progressDialog3.dismiss();
                                            progressDialog1.dismiss();
                                            progressDialog.dismiss();
                                            verificationStatus.setText("Approved !");
                                        } else if (VerificationStatus.equalsIgnoreCase("false")) {
                                            progressDialog3.dismiss();
                                            progressDialog1.dismiss();
                                            progressDialog.dismiss();
                                            verificationStatus.setText("Under Review");
                                            verificationStatus.setTextColor(Color.parseColor("#FFE31F"));
                                        }
                                        spelizationHome.setText(snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                        if (Application_Status != null) {
                                            patient_scrollView.setVisibility(View.GONE);
                                            doctor_scrollView.setVisibility(View.VISIBLE);
                                            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                progressDialog3.dismiss();
                                                progressDialog1.dismiss();
                                                progressDialog.dismiss();
                                                uid = FirebaseAuth.getInstance().getUid();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        progressDialog3.dismiss();
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                        nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            } else {
                                                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            }
                                        } else {
                                            patient_scrollView.setVisibility(View.VISIBLE);
                                            doctor_scrollView.setVisibility(View.GONE);
                                            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                uid = FirebaseAuth.getInstance().getUid();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                        nametv.setText("Hello, " + name);
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            } else {
                                                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        nametv.setText("Hello, " + name);
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog3.dismiss();
                                        progressDialog1.dismiss();
                                        progressDialog.dismiss();
                                    }
                                });
                            }else
                            {
                                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Application_Status = snapshot.child(STATES).child(DISTRICT).child(uid).child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                        VerificationStatus = snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class);
                                        spelizationHome.setText(snapshot.child(STATES).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                        if (VerificationStatus!=null && spelizationHome!=null){
                                            if (VerificationStatus.equalsIgnoreCase("true")) {
                                                verificationStatus.setText("Approved !");
                                            } else if (VerificationStatus.equalsIgnoreCase("false")) {
                                                verificationStatus.setText("Under Review");
                                                verificationStatus.setTextColor(Color.parseColor("#FFE31F"));
                                            }
                                        }
                                        if (Application_Status != null) {
                                            patient_scrollView.setVisibility(View.GONE);
                                            doctor_scrollView.setVisibility(View.VISIBLE);
                                            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                uid = FirebaseAuth.getInstance().getUid();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                        nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog.dismiss();

                                                    }
                                                });
                                            } else {
                                                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        nametv_doctor.setText("Hi, Dr. " + name + " " + lastname);
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            }
                                        } else {
                                            patient_scrollView.setVisibility(View.VISIBLE);
                                            doctor_scrollView.setVisibility(View.GONE);
                                            if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) == null) {
                                                uid = FirebaseAuth.getInstance().getUid();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("last_name").getValue(String.class);
                                                        nametv.setText("Hello, " + name);
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            } else {
                                                uid = GoogleSignIn.getLastSignedInAccount(MainActivity.this).getId();
                                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        name = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        lastname = snapshot.child(uid).child("first_name").getValue(String.class);
                                                        nametv.setText("Hello, " + name);
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressDialog3.dismiss();
                                                        progressDialog1.dismiss();
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog3.dismiss();
                                        progressDialog1.dismiss();
                                        progressDialog.dismiss();
                                    }
                                });
                            }

                        }else{
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            progressDialog3.dismiss();
                            progressDialog1.dismiss();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog3.dismiss();
                        progressDialog1.dismiss();
                        progressDialog.dismiss();
                    }
                });
            }
            privacypolicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://www.freeprivacypolicy.com/live/02650b0d-d309-4526-8f1e-b2297a6ea9bb";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            privacypolicy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://www.freeprivacypolicy.com/live/02650b0d-d309-4526-8f1e-b2297a6ea9bb";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            });
            contactus1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ContactPage.class);
                    startActivity(intent);
                }
            });
            mypatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MyPatient.class);
                    startActivity(intent);
                }
            });
            contactus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ContactPage.class);
                    startActivity(intent);
                }
            });
            mypatientrequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegularPatient.class);
                    startActivity(intent);
                }
            });
            mydoctors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MyDoctor.class);
                    startActivity(intent);
                }
            });
            seedoctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DoctorShow.class);
                    startActivity(intent);
                }
            });
            checkdisease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, CheckDisease.class);
                    startActivity(intent);
                }
            });
            govschemes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, GovSchemes.class);
                    startActivity(intent);
                }
            });
            seeprescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DownloadPrescription.class);
                    startActivity(intent);
                }
            });
            profile_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            });
            previouspatient_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, GiveMedicine.class);
                    startActivity(intent);
                }
            });
            editVerification_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DoctorFillForm.class);
                    startActivity(intent);
                }
            });
            sendinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ReadSensorData.class);
                    startActivity(intent);
                }
            });
            joinmeeting_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                }
            });
            govschemes_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, GovSchemes.class);
                    startActivity(intent);
                }
            });
            sentprescription_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, GiveMedicine.class);
                    intent.putExtra("From", "Yes");
                    startActivity(intent);
                }
            });
            booked_slot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, BookedSLots.class);
                    intent.putExtra("From", "Yes");
                    startActivity(intent);
                }
            });
            change_schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DoctorFillForm6.class);
                    startActivity(intent);
                }
            });
            covidcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, CovidTracker.class);
                    startActivity(intent);
                }
            });
            logout_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(MainActivity.this);
                    builder.setMessage("Please,  Confirm that you are sure to log out of your account. You can log in again with the same account anytime");
                    builder.setTitle("Are You Sure?");
                    builder.setCancelable(false);
                    builder.setNegativeButton(
                            "Log Out",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) != null) {
                                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                                build();
                                        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                                        googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
            joinmeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                }
            });
            previousdoctors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PreviousDoctors.class);
                    startActivity(intent);
                }
            });
            privacypolicy.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        profile.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            contactus1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        contactus1.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            contactus.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        contactus.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            profile.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        profile.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            privacypolicy1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        profile.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            mypatient.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mypatient.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            mypatientrequest.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mypatientrequest.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            mydoctors.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mydoctors.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            previousdoctors.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        previousdoctors.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            sendinfo.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        sendinfo.startAnimation(scale_up);
                    }
                    return false;
                }
            });
            booked_slot.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        booked_slot.startAnimation(scale_up);
                    }
                    return false;
                }
            });
            covidcheck.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        covidcheck.startAnimation(scale_up);
                    }
                    return false;
                }
            });
            checkdisease.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        checkdisease.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            seedoctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        seedoctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            govschemes.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        govschemes.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            seeprescription.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        seeprescription.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            joinmeeting.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        joinmeeting.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            profile_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        profile_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            previouspatient_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        previouspatient_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            editVerification_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        editVerification_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            joinmeeting_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        joinmeeting_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            govschemes_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        govschemes_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            sentprescription_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        sentprescription_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            change_schedule.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        change_schedule.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            logout_doctor.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        logout_doctor.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            logout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        logout.startAnimation(scale_up);

                    }
                    return false;
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(MainActivity.this);
                    builder.setMessage("Please,  Confirm that you are sure to log out of your account. You can log in again with the same account anytime");
                    builder.setTitle("Are You Sure?");
                    builder.setCancelable(false);
                    builder.setNegativeButton(
                            "Log Out",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    if (GoogleSignIn.getLastSignedInAccount(MainActivity.this) != null) {
                                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                                build();
                                        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                                        googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
    }
}